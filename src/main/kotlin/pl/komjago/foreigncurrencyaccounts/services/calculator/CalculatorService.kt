package pl.komjago.foreigncurrencyaccounts.services.calculator

import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import java.math.BigDecimal

interface CalculatorService {
    fun exchange(currency: Currency, amount: BigDecimal, target: Currency): BigDecimal

}