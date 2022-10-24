package pl.komjago.foreigncurrencyaccounts.services.accounts

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import pl.komjago.foreigncurrencyaccounts.getTestRegisterAccountInput
import pl.komjago.foreigncurrencyaccounts.repositories.AccountRepository
import pl.komjago.foreigncurrencyaccounts.repositories.CustomerRepository
import pl.komjago.foreigncurrencyaccounts.repositories.SubAccountRepository

class ForeignCurrencyAccountsServiceTests {

    private val accountRepository: AccountRepository = mockk(relaxed = true)
    private val subAccountRepository: SubAccountRepository = mockk(relaxed = true)
    private val customerRepository: CustomerRepository = mockk(relaxed = true)

    private val serviceUnderTest =
        ForeignCurrencyAccountsService(accountRepository, subAccountRepository, customerRepository)

    @Nested
    inner class Register{
        @Test
        fun `duplicate account throws DuplicateAccountException`() {
            val input = getTestRegisterAccountInput()

            every { accountRepository.existsByCustomerPesel(any()) } returns true

            assertThrows<DuplicateAccountException> {
                serviceUnderTest.register(input)
            }
        }
    }

    @Nested
    inner class Get{
        private val pesel = "81021964592"

        @Test
        fun `non-persisted pesel throws NoAccountException`() {
            every { accountRepository.findByCustomerPesel(any()) } returns null

            assertThrows<NoAccountException> {
                serviceUnderTest.get(pesel)
            }
        }
    }
}