package pl.komjago.foreigncurrencyaccounts.domain.exceptions

class RemoteServiceUnavailableException(serviceName: String) :
    RuntimeException("$serviceName is unavailable")