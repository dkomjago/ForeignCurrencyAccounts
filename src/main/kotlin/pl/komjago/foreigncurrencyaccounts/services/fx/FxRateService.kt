package pl.komjago.foreigncurrencyaccounts.services.fx

import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.services.fx.dto.FxRate

interface FxRateService {
    fun fetch(currency: Currency): FxRate
}