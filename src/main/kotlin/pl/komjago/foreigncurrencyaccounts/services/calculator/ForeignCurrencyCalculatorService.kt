package pl.komjago.foreigncurrencyaccounts.services.calculator

import org.springframework.stereotype.Service
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.services.fx.FxRateService
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ForeignCurrencyCalculatorService(
    private val nbpFxRateService: FxRateService
) : CalculatorService {

    override fun exchange(currency: Currency, amount: BigDecimal, target: Currency): BigDecimal {
        val targetCurrencyIsForeign = target != Currency.PLN
        val foreignCurrency = if (targetCurrencyIsForeign) target else currency
        val fxRate = nbpFxRateService.fetch(foreignCurrency)

        return amount.let {
            if (targetCurrencyIsForeign) it.setScale(fxRate.askPrice.scale())
                .divide(fxRate.askPrice, RoundingMode.HALF_EVEN) else it * fxRate.bidPrice
        }
    }
}