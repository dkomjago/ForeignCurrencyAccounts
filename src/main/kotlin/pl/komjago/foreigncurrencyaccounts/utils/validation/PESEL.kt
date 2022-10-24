package pl.komjago.foreigncurrencyaccounts.utils.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [PESELValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class PESEL(
        val message: String = "Invalid Pesel.",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Any>> = []
)