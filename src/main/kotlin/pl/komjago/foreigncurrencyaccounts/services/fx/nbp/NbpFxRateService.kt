package pl.komjago.foreigncurrencyaccounts.services.fx.nbp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.services.fx.FxRateService
import pl.komjago.foreigncurrencyaccounts.services.remote.HttpClient
import pl.komjago.foreigncurrencyaccounts.services.fx.dto.FxRate

@Service
class NbpFxRateService(
    val nbpHttpClient: HttpClient,
    val objectMapper: ObjectMapper
): FxRateService {

    override fun fetch(currency: Currency): FxRate {
        val json = nbpHttpClient.get("exchangerates/rates/c/$currency", mapOf("format" to "json"))
        return objectMapper.readValue(json)
    }
}