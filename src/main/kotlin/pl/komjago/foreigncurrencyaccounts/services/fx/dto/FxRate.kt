package pl.komjago.foreigncurrencyaccounts.services.fx.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import pl.komjago.foreigncurrencyaccounts.services.fx.nbp.FxRateDeserializer
import java.math.BigDecimal

@JsonDeserialize(using = FxRateDeserializer::class)
data class FxRate(
    val currency: String,
    val bidPrice: BigDecimal,
    val askPrice: BigDecimal
)
