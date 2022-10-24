package pl.komjago.foreigncurrencyaccounts.services.accounts

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.AccountInfo
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.CustomerInfo
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.RegisterAccountInput
import pl.komjago.foreigncurrencyaccounts.domain.Account
import pl.komjago.foreigncurrencyaccounts.domain.Customer
import pl.komjago.foreigncurrencyaccounts.domain.SubAccount
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import pl.komjago.foreigncurrencyaccounts.repositories.AccountRepository
import pl.komjago.foreigncurrencyaccounts.repositories.CustomerRepository
import pl.komjago.foreigncurrencyaccounts.repositories.SubAccountRepository
import java.math.BigDecimal

@Service
class ForeignCurrencyAccountsService(
    private val accountRepository: AccountRepository,
    private val subAccountRepository: SubAccountRepository,
    private val customerRepository: CustomerRepository
) : AccountsService {

    override fun get(pesel: String): AccountInfo {
        val account = accountRepository.findByCustomerPesel(pesel) ?: throw NoAccountException()

        val funds = account.subAccounts.associate {
            it.currency to it.funds
        }

        val customerInfo = account.customer.let {
            CustomerInfo(
                it.pesel,
                it.name,
                it.surname,
                it.birthdate
            )
        }

        return AccountInfo(
            customerInfo,
            funds
        )
    }

    @Transactional
    override fun register(registerAccountInput: RegisterAccountInput) {
        if (accountRepository.existsByCustomerPesel(registerAccountInput.customerInfo.pesel))
            throw DuplicateAccountException()

        val customer = registerAccountInput.customerInfo.run {
            Customer(pesel, name, surname, birthdate)
        }.let {
            customerRepository.save(it)
        }

        val account = Account(customer).let {
            accountRepository.save(it)
        }

        SubAccount(Currency.PLN, registerAccountInput.startingSum, account).let {
            subAccountRepository.save(it)
        }

        Currency.values().forEach { currency ->
            if (currency != Currency.PLN)
                SubAccount(currency, BigDecimal.ZERO, account).let {
                    subAccountRepository.save(it)
                }
        }
    }

}