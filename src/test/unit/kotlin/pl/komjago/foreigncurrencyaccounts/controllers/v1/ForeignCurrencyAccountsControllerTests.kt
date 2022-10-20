package pl.komjago.foreigncurrencyaccounts.controllers.v1

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ForeignCurrencyAccountsControllerTests {

    private val serviceUnderTest = ForeignCurrencyAccountsController()

    @Nested
    inner class Register{
        @Test
        fun `malformed name throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }

        @Test
        fun `malformed surname throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }

        @Test
        fun `malformed PESEL throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }

        @Test
        fun `duplicate account throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }

        @Test
        fun `underage customer throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }
    }

    @Nested
    inner class Get{
        @Test
        fun `returns multiple accounts`() {
            serviceUnderTest.register()
        }
    }
}