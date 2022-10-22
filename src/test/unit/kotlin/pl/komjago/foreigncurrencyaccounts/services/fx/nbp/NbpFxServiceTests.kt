package pl.komjago.foreigncurrencyaccounts.services.fx.nbp

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NbpFxServiceTests {

    private val serviceUnderTest = NbpFxRateService()

    @Nested
    inner class Fetch{
        @Test
        fun `malformed json throws`() {
            serviceUnderTest.fetch()
        }

        @Test
        fun `empty json throws`() {
            serviceUnderTest.fetch()
        }
    }
}