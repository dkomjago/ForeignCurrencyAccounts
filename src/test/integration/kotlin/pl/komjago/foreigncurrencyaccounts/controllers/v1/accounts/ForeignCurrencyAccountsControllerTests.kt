package pl.komjago.foreigncurrencyaccounts.controllers.v1.accounts

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
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.DuplicateAccountException
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.NoAccountException
import pl.komjago.foreigncurrencyaccounts.getTestCustomerInfo
import pl.komjago.foreigncurrencyaccounts.getTestRegisterAccountInput
import pl.komjago.foreigncurrencyaccounts.services.accounts.ForeignCurrencyAccountsService
import java.time.LocalDate

@WebMvcTest(ForeignCurrencyAccountsController::class)
class ForeignCurrencyAccountsControllerTests @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper,
    @MockkBean(relaxed = true) private val logger: Logger,
    @MockkBean(relaxed = true) val foreignCurrencyAccountsService: ForeignCurrencyAccountsService
) {

    @Nested
    inner class Register {
        @Test
        fun `malformed name returns 400`() {
            val customerInfo = getTestCustomerInfo(name = "123")
            val input = getTestRegisterAccountInput(customerInfo = customerInfo)

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `malformed surname returns 400`() {
            val customerInfo = getTestCustomerInfo(surname = "123")
            val input = getTestRegisterAccountInput(customerInfo = customerInfo)

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `malformed PESEL returns 400`() {
            val customerInfo = getTestCustomerInfo(pesel = "123")
            val input = getTestRegisterAccountInput(customerInfo = customerInfo)

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `DuplicateAccountException returns 409`() {
            val input = getTestRegisterAccountInput()

            every { foreignCurrencyAccountsService.register(any()) } throws DuplicateAccountException()

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isConflict() }
            }
        }

        @Test
        fun `underage customer returns 400`() {
            val customerInfo = getTestCustomerInfo(birthdate = LocalDate.now().minusYears(17).minusDays(364))
            val input = getTestRegisterAccountInput(customerInfo = customerInfo)

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `correct input returns 201`() {
            val input = getTestRegisterAccountInput()

            mvc.put("/api/v1/accounts/register") {
                content = mapper.writeValueAsString(input)
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isCreated() }
            }
        }
    }

    @Nested
    inner class Get {
        @Test
        fun `malformed PESEL returns 400`() {
            val pesel = "123"

            mvc.get("/api/v1/accounts/$pesel").andExpect {
                status { isBadRequest() }
            }
        }

        @Test
        fun `correct PESEL returns 200`() {
            val pesel = "81021964593"

            mvc.get("/api/v1/accounts/$pesel").andExpect {
                status { isOk() }
            }
        }

        @Test
        fun `NoAccountException returns 204`() {
            val pesel = "81021964593"

            every { foreignCurrencyAccountsService.get(any()) } throws NoAccountException()

            mvc.get("/api/v1/accounts/$pesel").andExpect {
                status { isNoContent() }
            }
        }
    }
}