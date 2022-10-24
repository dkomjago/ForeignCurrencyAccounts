package pl.komjago.foreigncurrencyaccounts.services.fx.nbp

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import pl.komjago.foreigncurrencyaccounts.domain.exceptions.RemoteServiceContractException
import pl.komjago.foreigncurrencyaccounts.services.fx.dto.FxRate

class FxRateDeserializer : StdDeserializer<FxRate>(FxRate::class.java) {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): FxRate {
        val responseNode: JsonNode = jp.codec.readTree(jp)
        val fxRateNode = responseNode.get("rates")?.get(0)
        val currencyNode = responseNode.get("code")
        val bidPriceNode =
            fxRateNode?.get("bid") ?: throw RemoteServiceContractException("Fx rate service", "No ask price in response")
        val askPriceNode =
            fxRateNode.get("ask") ?: throw RemoteServiceContractException("Fx rate service", "No bid price in response")
        return FxRate(
            currencyNode.textValue(),
            bidPriceNode.decimalValue(),
            askPriceNode.decimalValue()
        )
    }
}