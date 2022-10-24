package pl.komjago.foreigncurrencyaccounts.services.remote.nbp

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.util.DefaultUriBuilderFactory
import pl.komjago.foreigncurrencyaccounts.services.remote.HttpClient
import pl.komjago.foreigncurrencyaccounts.services.remote.HttpErrorHandler
import pl.komjago.foreigncurrencyaccounts.services.remote.nbp.config.NbpClientProperties

@Service
class NbpHttpClient(val properties: NbpClientProperties): HttpClient {

    private final val clientHttpRequestFactory = HttpComponentsClientHttpRequestFactory().apply {
        setReadTimeout(properties.timeout.toMillis().toInt())
    }

    val restTemplate = RestTemplate(clientHttpRequestFactory).apply {
        uriTemplateHandler = DefaultUriBuilderFactory(properties.baseUrl)
        errorHandler = HttpErrorHandler("Nbp")
    }

    override fun get(url: String, parameters: Map<String, String>): String {
        return restTemplate.getForObject(properties.baseUrl + url, parameters)
    }
}