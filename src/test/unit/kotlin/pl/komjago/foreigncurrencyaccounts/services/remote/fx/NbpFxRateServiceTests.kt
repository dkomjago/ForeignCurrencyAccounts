package pl.komjago.foreigncurrencyaccounts.services.remote.fx

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NbpFxRateServiceTests {

    private val serviceUnderTest = NbpFxRateService()

    @Nested
    inner class Exchange{
        @Test
        fun `gets remote data`() {
            serviceUnderTest.fetch()
        }
    }
}