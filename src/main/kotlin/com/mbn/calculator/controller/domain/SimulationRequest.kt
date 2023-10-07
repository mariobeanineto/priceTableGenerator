package com.mbn.calculator.controller.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SimulationRequest(
        @JsonProperty("documentNumber")
        val documentNumber: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("amount")
        val amount: BigDecimal,
        @JsonProperty("installmentNumber")
        val installmentNumber: Int
)
