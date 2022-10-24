package pl.komjago.foreigncurrencyaccounts.controllers.v1.funds

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.komjago.foreigncurrencyaccounts.controllers.v1.funds.dto.ExchangeFundsInput
import pl.komjago.foreigncurrencyaccounts.services.funds.FundsService
import pl.komjago.foreigncurrencyaccounts.utils.validation.PESEL
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("/api/v1/accounts/{pesel}/funds")
class ForeignCurrencyFundsController(
    private val foreignCurrencyFundsService: FundsService
) {

    @PostMapping("/exchange")
    fun exchange(
        @PathVariable @PESEL pesel: String,
        @RequestBody @Valid exchangeFundsInput: ExchangeFundsInput
    ) {
        foreignCurrencyFundsService.exchange(pesel, exchangeFundsInput)
    }
}