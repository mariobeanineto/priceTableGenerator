package com.mbn.calculator.implementation.concrete.domain.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SketchRequest(
        @JsonProperty("documentNumber")
        val documentNumber: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("amount")
        val amount: BigDecimal,
        @JsonProperty("installmentNumber")
        val installmentNumber: List<Int>
)
