package pl.komjago.foreigncurrencyaccounts.controllers

import com.fasterxml.jackson.annotation.JsonInclude

data class ErrorResponse(
    val message: String,
    val status: Int,
    @JsonInclude(JsonInclude.Include.NON_NULL) val payload: Any? = null
)