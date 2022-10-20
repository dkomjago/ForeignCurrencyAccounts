package pl.komjago.foreigncurrencyaccounts.services.accounts

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ForeignCurrencyAccountsServiceTests {

    private val serviceUnderTest = ForeignCurrencyAccountsService()

    @Nested
    inner class Register{
        @Test
        fun runs() {
            serviceUnderTest.register()
        }

        @Test
        fun `can register`() {
            serviceUnderTest.register()
        }
    }

    @Nested
    inner class Get{
        @Test
        fun `can get`() {
            serviceUnderTest.register()
        }
    }
}