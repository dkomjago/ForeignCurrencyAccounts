package pl.komjago.foreigncurrencyaccounts.services.funds

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ForeignCurrencyFundsServiceTests {

    private val serviceUnderTest = ForeignCurrencyFundsService()

    @Nested
    inner class Exchange {
        @Test
        fun `calls calculator to exchange currency`() {
            serviceUnderTest.exchange()
        }

        @Test
        fun `calls adjust funds`() {
            serviceUnderTest.exchange()
        }
    }

    @Nested
    inner class AdjustAccountFunds {
        @Test
        fun `balance below 0 throws`() {
            serviceUnderTest.exchange()
        }
    }
}