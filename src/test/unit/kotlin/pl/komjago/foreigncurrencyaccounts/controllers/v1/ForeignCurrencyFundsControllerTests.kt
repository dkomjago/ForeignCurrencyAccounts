package pl.komjago.foreigncurrencyaccounts.controllers.v1

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ForeignCurrencyFundsControllerTests {

    private val serviceUnderTest = ForeignCurrencyFundsController()

    @Nested
    inner class Exchange{

        @Test
        fun `correct input doesn't throw`() {
            assertDoesNotThrow<Exception> {
                serviceUnderTest.exchange()
            }
        }

        @Test
        fun `invalid currency throws`() {
            assertThrows<Exception> {
                serviceUnderTest.exchange()
            }
        }

        @Test
        fun `invalid target currency throws`() {
            assertThrows<Exception> {
                serviceUnderTest.exchange()
            }
        }

        @Test
        fun `duplicate currency throws`() {
            assertThrows<Exception> {
                serviceUnderTest.exchange()
            }
        }

        @Test
        fun `non-positive amount throws`() {
            assertThrows<Exception> {
                serviceUnderTest.exchange()
            }
        }

        @Test
        fun `malformed PESEL throws`() {
            assertThrows<Exception> {
                serviceUnderTest.exchange()
            }
        }
    }
}