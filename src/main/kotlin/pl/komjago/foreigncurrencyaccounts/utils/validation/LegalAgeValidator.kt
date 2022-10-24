package pl.komjago.foreigncurrencyaccounts.utils.validation

import java.time.LocalDate
import java.time.Period
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class LegalAgeValidator : ConstraintValidator<LegalAge, LocalDate> {
    override fun initialize(legalAgeConstraint: LegalAge) {}

    override fun isValid(birtdate: LocalDate, cxt: ConstraintValidatorContext?): Boolean =
        Period.between(birtdate, LocalDate.now()).years >= 18
}