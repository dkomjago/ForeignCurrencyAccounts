package pl.komjago.foreigncurrencyaccounts.services.funds

import pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto.ExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.domain.Account
import java.math.BigDecimal

interface FundsService {
    fun exchange(pesel: String, exchangeFundsInput: ExchangeFundsInput)
    fun adjustAccountFunds(account: Account, exchangeFundsInput: ExchangeFundsInput, exchangedAmount: BigDecimal)
}