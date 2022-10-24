package pl.komjago.foreigncurrencyaccounts.services.remote

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceContractException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceUnavailableException

class HttpErrorHandler(private val serviceName: String) : ResponseErrorHandler {

    override fun hasError(response: ClientHttpResponse): Boolean =
        response.statusCode.series() == HttpStatus.Series.CLIENT_ERROR
                || response.statusCode.series() == HttpStatus.Series.SERVER_ERROR

    override fun handleError(response: ClientHttpResponse) {
        if (response.statusCode
                .series() === HttpStatus.Series.SERVER_ERROR
        ) {
            throw RemoteServiceUnavailableException(serviceName)
        } else if (response.statusCode
                .series() === HttpStatus.Series.CLIENT_ERROR
        ) {
            throw RemoteServiceContractException(serviceName)
        }
    }
}