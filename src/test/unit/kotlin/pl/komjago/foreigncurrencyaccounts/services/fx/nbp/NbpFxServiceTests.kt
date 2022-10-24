package pl.komjago.foreigncurrencyaccounts.services.fx.nbp

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceContractException
import pl.komjago.foreigncurrencyaccounts.services.remote.HttpClient

class NbpFxServiceTests {

    private val nbpHttpClient: HttpClient = mockk(relaxed = true)
    private val objectMapper = jacksonObjectMapper()

    private val serviceUnderTest = NbpFxRateService(nbpHttpClient, objectMapper)

    @Nested
    inner class Fetch{
        @Test
        fun `malformed json throws ServiceContractException`() {
            every { nbpHttpClient.get(any(), any()) } returns  "{\"somedata\":\"\"}"

            assertThrows<RemoteServiceContractException> {
                serviceUnderTest.fetch(Currency.PLN)
            }
        }

        @Test
        fun `empty json throws ServiceContractException`() {
            every { nbpHttpClient.get(any(), any()) } returns  "{}"

            assertThrows<RemoteServiceContractException> {
                serviceUnderTest.fetch(Currency.PLN)
            }
        }
    }
}