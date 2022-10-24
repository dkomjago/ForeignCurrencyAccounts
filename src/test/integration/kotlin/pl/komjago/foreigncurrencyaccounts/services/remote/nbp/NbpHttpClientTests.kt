package pl.komjago.foreigncurrencyaccounts.services.remote.nbp

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockserver.client.MockServerClient
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import org.mockserver.springtest.MockServerTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceContractException

@SpringBootTest
@MockServerTest("remote.nbp.base-url=http://localhost:\${mockServerPort}/")
class NbpHttpClientTests {

    private lateinit var mockServerClient: MockServerClient

    @Autowired
    private lateinit var serviceUnderTest: NbpHttpClient

    @Nested
    inner class Get{
        @Test
        fun `code 4xx throws`() {
            mockServerClient.`when`(
                request().withMethod("POST").withPath("/")
            ).respond(
                response().withStatusCode(400)
            )

            assertThrows<RemoteServiceContractException> {
                serviceUnderTest.get("", emptyMap())
            }
        }

        @Test
        fun `code 5xx throws`() {
            mockServerClient.`when`(
                request().withMethod("POST").withPath("/")
            ).respond(
                response().withStatusCode(500)
            )

            assertThrows<RemoteServiceContractException> {
                serviceUnderTest.get("", emptyMap())
            }
        }
    }
}