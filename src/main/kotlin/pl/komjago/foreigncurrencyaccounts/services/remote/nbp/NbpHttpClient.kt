package pl.komjago.foreigncurrencyaccounts.services.remote.nbp

import org.springframework.stereotype.Service
import pl.komjago.foreigncurrencyaccounts.services.remote.HttpClient

@Service
class NbpHttpClient: HttpClient {

    override fun get(url: String, parameters: Map<String, String>): String {
        TODO("Not yet implemented")
    }
}