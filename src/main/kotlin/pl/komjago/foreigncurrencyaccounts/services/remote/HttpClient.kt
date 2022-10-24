package pl.komjago.foreigncurrencyaccounts.services.remote

interface HttpClient {
    fun get(url: String, parameters: Map<String, String>): String
}