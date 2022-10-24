package pl.komjago.foreigncurrencyaccounts.domain.exceptions

class NoFundsException : RuntimeException("Sub account doesn't have enough funds for this operation")