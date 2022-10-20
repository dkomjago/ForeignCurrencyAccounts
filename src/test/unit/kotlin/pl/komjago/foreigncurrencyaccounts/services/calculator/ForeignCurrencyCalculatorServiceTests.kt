package pl.komjago.foreigncurrencyaccounts.services.calculator

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ForeignCurrencyCalculatorServiceTests {

    private val serviceUnderTest = ForeignCurrencyCalculatorService()

    @Nested
    inner class Exchange{
        @Test
        fun `retrieves fx rates`() {
            serviceUnderTest.exchange()
        }

        @Test
        fun `can exchange PLN to USD`() {
            serviceUnderTest.exchange()
        }

        @Test
        fun `can exchange USD to PLN`() {
            serviceUnderTest.exchange()
        }
    }
}