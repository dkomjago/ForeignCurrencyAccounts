package pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto

import com.fasterxml.jackson.annotation.JsonUnwrapped
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import java.math.BigDecimal

data class AccountInfo(
    @JsonUnwrapped
    val customerInfo: CustomerInfo,
    val funds: Map<Currency, BigDecimal>
)