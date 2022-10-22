package pl.komjago.foreigncurrencyaccounts.services.funds

import org.springframework.stereotype.Service
import pl.komjago.foreigncurrencyaccounts.controllers.v1.dto.ExchangeFundsInput

@Service
class ForeignCurrencyFundsService(
) : FundsService {
    override fun exchange(pesel: String, exchangeFundsInput: ExchangeFundsInput) {
        TODO("Not yet implemented")
    }

    override fun adjustAccountFunds(
    ) {
        TODO("Not yet implemented")
    }
}