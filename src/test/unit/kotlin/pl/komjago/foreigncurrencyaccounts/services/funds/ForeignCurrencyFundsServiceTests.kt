package pl.komjago.foreigncurrencyaccounts.services.funds

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.komjago.foreigncurrencyaccounts.defaultTestPesel
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoFundsException
import pl.komjago.foreigncurrencyaccounts.getTestAccount
import pl.komjago.foreigncurrencyaccounts.getTestExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.getTestSubAccount
import pl.komjago.foreigncurrencyaccounts.repositories.AccountRepository
import pl.komjago.foreigncurrencyaccounts.repositories.SubAccountRepository
import pl.komjago.foreigncurrencyaccounts.services.calculator.CalculatorService
import java.math.BigDecimal

class ForeignCurrencyFundsServiceTests {

    private val accountRepository: AccountRepository = mockk(relaxed = true)
    private val subAccountRepository: SubAccountRepository = mockk(relaxed = true)
    private val foreignCurrencyCalculatorService: CalculatorService = mockk(relaxed = true)

    private val serviceUnderTest =
        ForeignCurrencyFundsService(accountRepository, subAccountRepository, foreignCurrencyCalculatorService)

    @Nested
    inner class Exchange {
        @Test
        fun `calls calculator to exchange currency`() {
            val testInput = getTestExchangeFundsInput()
            every { accountRepository.findByCustomerPesel(defaultTestPesel) } returns getTestAccount()
            serviceUnderTest.exchange(defaultTestPesel, testInput)

            verify { foreignCurrencyCalculatorService.exchange(testInput.currency, testInput.amount, testInput.target) }
        }

        @Test
        fun `calls adjust funds`() {
            val testInput = getTestExchangeFundsInput()
            val testAccount = getTestAccount()
            every { accountRepository.findByCustomerPesel(defaultTestPesel) } returns testAccount
            val spiedService = spyk(serviceUnderTest)

            spiedService.exchange(defaultTestPesel, testInput)

            verify { spiedService.adjustAccountFunds(testAccount, testInput, any()) }
        }
    }

    @Nested
    inner class AdjustAccountFunds {
        @Test
        fun `balance below 0 throws NoFundsException`() {
            val testInput = getTestExchangeFundsInput()
            val testAccount = getTestAccount(
                subAccounts = listOf(
                    getTestSubAccount(currency = Currency.PLN, funds = BigDecimal.ZERO),
                    getTestSubAccount(currency = Currency.USD, funds = BigDecimal.ZERO),
                )
            )

            assertThrows<NoFundsException> {
                serviceUnderTest.adjustAccountFunds(testAccount, testInput, BigDecimal.TEN)
            }
        }
    }
}