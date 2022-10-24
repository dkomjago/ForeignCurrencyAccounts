package pl.komjago.foreigncurrencyaccounts.domain.exceptions

class RemoteServiceContractException(serviceName: String, reason: String? = null) :
    RuntimeException("Failed request to $serviceName${reason?.let { ": $it" }}")