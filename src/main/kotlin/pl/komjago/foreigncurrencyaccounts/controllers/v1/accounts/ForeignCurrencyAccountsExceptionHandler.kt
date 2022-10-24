package pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts

import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.komjago.foreigncurrencyaccounts.controllers.ErrorResponse
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import javax.validation.ConstraintViolationException

@RestControllerAdvice(basePackageClasses = [ForeignCurrencyAccountsController::class])
class ForeignCurrencyAccountsExceptionHandler(private val logger: Logger) {

    private val messageNotAvailable = "Message not available."

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.BAD_REQUEST.value(),
            ex.constraintViolations.associate { it.propertyPath.last() to it.invalidValue }
        ).logged()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDuplicateAccountException(ex: DuplicateAccountException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.CONFLICT.value()
        ).logged()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun handleDuplicateAccountException(ex: NoAccountException) {
        ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.NO_CONTENT.value()
        ).logged()
    }

    fun ErrorResponse.logged(): ErrorResponse {
        logger.debug("Handled ForeignCurrencyAccountsController exception: ${this.message} ")
        return this
    }
}