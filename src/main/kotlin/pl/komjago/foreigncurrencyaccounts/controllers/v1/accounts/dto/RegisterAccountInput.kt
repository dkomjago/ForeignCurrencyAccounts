package pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto

import java.math.BigDecimal
import javax.validation.Valid

data class RegisterAccountInput(
    @field:Valid
    val customerInfo: CustomerInfo,
    val startingSum: BigDecimal
)