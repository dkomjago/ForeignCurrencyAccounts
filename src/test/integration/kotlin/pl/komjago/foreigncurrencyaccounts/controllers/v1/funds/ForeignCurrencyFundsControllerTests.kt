package pl.komjago.foreigncurrencyaccounts.controllers.v1.funds

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateCurrencyException
import pl.komjago.foreigncurrencyaccounts.getTestExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.services.funds.ForeignCurrencyFundsService
import java.math.BigDecimal

@WebMvcTest(ForeignCurrencyFundsController::class)
class ForeignCurrencyFundsControllerTests @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
    @MockkBean(relaxed = true) private val logger: Logger,
    @MockkBean(relaxed = true) val foreignCurrencyAccountsService: ForeignCurrencyFundsService
) {

    @Nested
    inner class Exchange{

        private val pesel = "81021964593"

        @Test
        fun `correct input returns 200`() {
            val input = getTestExchangeFundsInput()

            mvc.post("/api/v1/accounts/$pesel/funds/exchange") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
            }
        }

        @Test
        fun `invalid currency returns 400`() {
            val input = getTestExchangeFundsInput()

            mvc.post("/api/v1/accounts/$pesel/funds/exchange") {
                content = mapper.writeValueAsString(input).replace("PLN", "TEST")
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `invalid target currency returns 400`() {
            val input = getTestExchangeFundsInput()

            mvc.post("/api/v1/accounts/$pesel/funds/exchange") {
                content = mapper.writeValueAsString(input).replace("USD", "TEST")
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `duplicateCurrencyException returns 400`() {
            val input = getTestExchangeFundsInput()

            every { foreignCurrencyAccountsService.exchange(any(), any()) } throws DuplicateCurrencyException()

            mvc.post("/api/v1/accounts/$pesel/funds/exchange") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `non-positive amount returns 400`() {
            val input = getTestExchangeFundsInput(amount = BigDecimal.ZERO)

            mvc.post("/api/v1/accounts/$pesel/funds/exchange") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `malformed PESEL returns 400`() {
            val malformedPesel = "123"
            val input = getTestExchangeFundsInput()

            mvc.post("/api/v1/accounts/$malformedPesel/funds/exchange") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }
}