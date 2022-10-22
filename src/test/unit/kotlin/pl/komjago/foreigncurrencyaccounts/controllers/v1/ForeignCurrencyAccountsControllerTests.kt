package pl.komjago.foreigncurrencyaccounts.controllers.v1

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
        fun `underage customer throws`() {
            assertThrows<Exception> {
                serviceUnderTest.register()
            }
        }

        @Test
        fun `correct input doesn't throw`() {
            assertDoesNotThrow<Exception> {
                serviceUnderTest.register()
            }
        }
    }

    @Nested
    inner class Get{
        @Test
        fun `malformed PESEL throws`() {
            assertThrows<Exception> {
                serviceUnderTest.get()
            }
        }

        @Test
        fun `correct PESEL returns account info`() {
            serviceUnderTest.get()
        }
    }
}