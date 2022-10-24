package pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto

import pl.komjago.foreigncurrencyaccounts.utils.validation.LegalAge
import pl.komjago.foreigncurrencyaccounts.utils.validation.PESEL
import java.time.LocalDate
import javax.validation.constraints.Pattern

data class CustomerInfo(
    @field:PESEL
    val pesel: String,
    @field:Pattern(regexp = "^\\p{Lu}\\p{Ll}+\$")
    val name: String,
    @field:Pattern(regexp = "^\\p{Lu}\\p{Ll}+(-\\p{Lu}\\p{Ll}+)?\$")
    val surname: String,
    @field:LegalAge
    val birthdate: LocalDate
)