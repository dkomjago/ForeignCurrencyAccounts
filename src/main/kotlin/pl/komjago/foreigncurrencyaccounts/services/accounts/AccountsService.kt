package pl.komjago.foreigncurrencyaccounts.services.accounts

import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.AccountInfo
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.RegisterAccountInput

interface AccountsService {

    fun get(pesel: String): AccountInfo

    fun register(registerAccountInput: RegisterAccountInput)

}