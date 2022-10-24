package pl.komjago.foreigncurrencyaccounts.utils.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [LegalAgeValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class LegalAge(
        val message: String = "Customer is not past legal age.",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Any>> = []
)