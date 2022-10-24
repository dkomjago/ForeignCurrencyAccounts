package pl.komjago.foreigncurrencyaccounts

import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.CustomerInfo
import pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto.ExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.RegisterAccountInput
import pl.komjago.foreigncurrencyaccounts.domain.Account
import pl.komjago.foreigncurrencyaccounts.domain.Customer
import pl.komjago.foreigncurrencyaccounts.domain.SubAccount
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.services.fx.dto.FxRate
import java.math.BigDecimal
import java.time.LocalDate

const val defaultTestPesel = "89092899462"

fun getTestExchangeFundsInput(
    currency: Currency = Currency.PLN,
    amount: BigDecimal = BigDecimal.ONE,
    target: Currency = Currency.USD
) = ExchangeFundsInput(currency, amount, target)

fun getTestRegisterAccountInput(
    customerInfo: CustomerInfo = getTestCustomerInfo(),
    startingSum: BigDecimal = BigDecimal.ZERO
) = RegisterAccountInput(customerInfo, startingSum)

fun getTestCustomerInfo(
    pesel: String = defaultTestPesel,
    name: String = "Tester",
    surname: String = "Testerson",
    birthdate: LocalDate = LocalDate.of(1989, 9, 28)
) = CustomerInfo(pesel, name, surname, birthdate)

fun getTestFxRate(
    currency: String = "USD",
    bidPrice: BigDecimal = BigDecimal.ONE,
    askPrice: BigDecimal = BigDecimal.ONE
) = FxRate(currency, bidPrice, askPrice)

fun getTestAccount(
    customer: Customer = getTestCustomer(),
    subAccounts: List<SubAccount> = emptyList(),
    id: Long = 1
) = Account(customer, subAccounts, id)

fun getTestCustomer(
    pesel: String = defaultTestPesel,
    name: String = "Tester",
    surname: String = "Testerson",
    birthdate: LocalDate = LocalDate.of(1989, 9, 28),
    id: Long = 1
) = Customer(pesel, name, surname, birthdate, id)

fun getTestSubAccount(
    currency: Currency = Currency.PLN,
    funds: BigDecimal = BigDecimal.ONE,
    account: Account? = getTestAccount(),
    id: Long = 1
) = SubAccount(currency, funds, account, id)