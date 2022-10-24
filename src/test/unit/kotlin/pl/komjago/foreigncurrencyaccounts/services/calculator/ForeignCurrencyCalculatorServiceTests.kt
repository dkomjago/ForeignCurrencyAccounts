package pl.komjago.foreigncurrencyaccounts.services.calculator

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import pl.komjago.foreigncurrencyaccounts.domain.enums.Currency
import pl.komjago.foreigncurrencyaccounts.getTestFxRate
import pl.komjago.foreigncurrencyaccounts.services.fx.FxRateService
import java.math.BigDecimal

class ForeignCurrencyCalculatorServiceTests {

    private val nbpFxRateService: FxRateService = mockk()

    private val serviceUnderTest = ForeignCurrencyCalculatorService(nbpFxRateService)

    @Nested
    inner class Exchange{
        @Test
        fun `retrieves fx rates`() {
            every { nbpFxRateService.fetch(any()) } returns getTestFxRate()

            serviceUnderTest.exchange(Currency.PLN, BigDecimal.ONE, Currency.USD)

            verify(exactly = 1) { nbpFxRateService.fetch(Currency.USD) }
        }

        @Test
        fun `can exchange PLN to USD`() {
            every { nbpFxRateService.fetch(any()) } returns getTestFxRate(askPrice = BigDecimal.TEN)

            val result = serviceUnderTest.exchange(Currency.PLN, BigDecimal.ONE, Currency.USD)

            assertEquals(BigDecimal.ONE / BigDecimal.TEN, result)
        }

        @Test
        fun `can exchange USD to PLN`() {
            every { nbpFxRateService.fetch(any()) } returns getTestFxRate(bidPrice = BigDecimal.TEN)

            val result = serviceUnderTest.exchange(Currency.USD, BigDecimal.ONE, Currency.PLN)

            assertEquals(BigDecimal.TEN, result)
        }
    }
}