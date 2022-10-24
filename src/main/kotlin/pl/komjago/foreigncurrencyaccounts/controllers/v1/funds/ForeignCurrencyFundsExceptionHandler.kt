package pl.komjago.foreigncurrencyaccounts.controllers.v1.funds

import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.komjago.foreigncurrencyaccounts.controllers.ErrorResponse
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateCurrencyException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceContractException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceUnavailableException
import javax.validation.ConstraintViolationException

@RestControllerAdvice(basePackageClasses = [ForeignCurrencyFundsController::class])
class ForeignCurrencyFundsExceptionHandler(private val logger: Logger) {

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNoAccountException(ex: NoAccountException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.BAD_REQUEST.value()
        ).logged()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleDuplicateCurrencyException(ex: DuplicateCurrencyException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.BAD_REQUEST.value()
        ).logged()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleRemoteServiceUnavailableException(ex: RemoteServiceUnavailableException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.SERVICE_UNAVAILABLE.value()
        ).logged()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRemoteServiceContractException(ex: RemoteServiceContractException): ErrorResponse {
        return ErrorResponse(
            ex.message ?: messageNotAvailable,
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        ).logged()
    }

    fun ErrorResponse.logged(): ErrorResponse {
        logger.debug("Handled ForeignCurrencyFundsController exception: ${this.message} ")
        return this
    }
}