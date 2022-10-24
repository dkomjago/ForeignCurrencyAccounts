package pl.komjago.foreigncurrencyaccounts.utils.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PESELValidator : ConstraintValidator<PESEL, String> {
    override fun initialize(legalAgeConstraint: PESEL) {}

    override fun isValid(pesel: String, cxt: ConstraintValidatorContext?): Boolean =
        "^\\d{11}\$".toRegex().matches(pesel)
}