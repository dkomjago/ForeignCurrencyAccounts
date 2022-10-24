package pl.komjago.foreigncurrencyaccounts

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts.dto.RegisterAccountInput
import pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto.ExchangeFundsInput
import java.math.BigDecimal


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ForeignCurrencyAccountsAPITests @Autowired constructor(
    private val testRestTemplate: TestRestTemplate
) {

    @Nested
    inner class Exchange {

        private fun register() {
            val input = getTestRegisterAccountInput(startingSum = BigDecimal.TEN)

            val httpEntity: HttpEntity<RegisterAccountInput> = HttpEntity(input, HttpHeaders())


            val responseEntity = testRestTemplate.exchange(
                "/api/v1/accounts/register",
                HttpMethod.PUT, httpEntity, String::class.java
            )

            assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        }

        private fun get() {
            val input = getTestRegisterAccountInput()

            val httpEntity: HttpEntity<RegisterAccountInput> = HttpEntity(input, HttpHeaders())


            val responseEntity = testRestTemplate.exchange(
                "/api/v1/accounts/${input.customerInfo.pesel}",
                HttpMethod.GET, httpEntity, String::class.java
            )

            assertEquals(HttpStatus.OK, responseEntity.statusCode)
        }

        private fun exchange() {
            val previousInput = getTestRegisterAccountInput()
            val input = getTestExchangeFundsInput()

            val httpEntity: HttpEntity<ExchangeFundsInput> = HttpEntity(input, HttpHeaders())


            val responseEntity = testRestTemplate.exchange(
                "/api/v1/accounts/${previousInput.customerInfo.pesel}/funds/exchange",
                HttpMethod.POST, httpEntity, String::class.java
            )

            assertEquals(HttpStatus.OK, responseEntity.statusCode)
        }

        @Test
        fun `can exchange`() {
            register()

            get()

            exchange()
        }
    }

}
