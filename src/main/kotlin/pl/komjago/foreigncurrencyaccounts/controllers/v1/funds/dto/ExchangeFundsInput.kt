package pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto

import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin

data class ExchangeFundsInput(
    val currency: Currency,
    @field:DecimalMin(value = "0", inclusive = false)
    val amount: BigDecimal,
    val target: Currency
)