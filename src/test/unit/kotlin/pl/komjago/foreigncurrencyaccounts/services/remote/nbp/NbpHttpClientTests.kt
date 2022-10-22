package pl.komjago.foreigncurrencyaccounts.services.remote.nbp

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NbpHttpClientTests {

    private val serviceUnderTest = NbpHttpClient()

    @Nested
    inner class Get{
        @Test
        fun `code 4xx throws`() {
            serviceUnderTest.get()
        }

        @Test
        fun `code 5xx throws`() {
            serviceUnderTest.get()
        }

        @Test
        fun `code != 200 throws`() {
            serviceUnderTest.get()
        }

        @Test
        fun `timeout throws`() {
            serviceUnderTest.get()
        }
    }
}