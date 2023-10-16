package com.mbn.calculator.implementation.concrete.domain.integration

import com.fasterxml.jackson.annotation.JsonProperty

data class SelicResponse(
        @JsonProperty("data")
        val date: String,
        @JsonProperty("valor")
        val value: String
)
