package pl.komjago.foreigncurrencyaccounts.services.accounts

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ForeignCurrencyAccountsServiceTests {

    private val serviceUnderTest = ForeignCurrencyAccountsService()

    @Nested
    inner class Register{
        @Test
        fun `duplicate account throws`() {
            serviceUnderTest.register()
        }
    }

    @Nested
    inner class Get{
        @Test
        fun `gets from repo`() {
            serviceUnderTest.register()
        }

        @Test
        fun `non-persisted pesel throws`() {
            serviceUnderTest.register()
        }
    }
}