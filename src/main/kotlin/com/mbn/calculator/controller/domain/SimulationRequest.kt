package com.mbn.calculator.controller.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class SimulationRequest(
        @JsonProperty("documentNumber")
        val documentNumber: String,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("amount")
        val amount: String,
        @JsonProperty("installmentNumber")
        val installmentNumber: Int
)
