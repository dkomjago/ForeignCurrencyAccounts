package pl.komjago.foreigncurrencyaccounts.services.funds

import pl.komjago.foreigncurrencyaccounts.controllers.v1.dto.ExchangeFundsInput

interface FundsService {
    fun exchange(pesel: String, exchangeFundsInput: ExchangeFundsInput)
    fun adjustAccountFunds()
}