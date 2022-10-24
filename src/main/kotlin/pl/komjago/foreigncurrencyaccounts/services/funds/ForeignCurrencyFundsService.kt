package pl.komjago.foreigncurrencyaccounts.services.funds

import org.springframework.stereotype.Service
import pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto.ExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.domain.Account
import pl.komjago.foreigncurrencyaccounts.domain.SubAccount
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoFundsException
import pl.komjago.foreigncurrencyaccounts.repositories.AccountRepository
import pl.komjago.foreigncurrencyaccounts.repositories.SubAccountRepository
import pl.komjago.foreigncurrencyaccounts.services.calculator.CalculatorService
import java.math.BigDecimal

@Service
class ForeignCurrencyFundsService(
    private val accountRepository: AccountRepository,
    private val subAccountRepository: SubAccountRepository,
    private val foreignCurrencyCalculatorService: CalculatorService
) : FundsService {
    override fun exchange(pesel: String, exchangeFundsInput: ExchangeFundsInput) {
        val account = accountRepository.findByCustomerPesel(pesel) ?: throw NoAccountException()

        val exchangedAmount = exchangeFundsInput.run {
            foreignCurrencyCalculatorService.exchange(currency, amount, target)
        }

        adjustAccountFunds(account, exchangeFundsInput, exchangedAmount)
    }

    override fun adjustAccountFunds(
        account: Account,
        exchangeFundsInput: ExchangeFundsInput,
        exchangedAmount: BigDecimal
    ) {
        with(exchangeFundsInput) {
            account.subAccounts.filter { it.currency == target || it.currency == currency }.map {
                val fundsAfterExchange = it.funds + if (it.currency == target) exchangedAmount else -amount

                if(fundsAfterExchange < BigDecimal.ZERO) throw NoFundsException()

                SubAccount(
                    it.currency,
                    fundsAfterExchange,
                    it.account,
                    it.id
                ).also { updatedSubAccount ->
                    subAccountRepository.save(updatedSubAccount)
                }
            }
        }
    }
}