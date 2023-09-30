package com.mbn.calculator.integration.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class SelicResponse(
        @JsonProperty("data")
        val data: String,
        @JsonProperty("valor")
        val valor: String
)
