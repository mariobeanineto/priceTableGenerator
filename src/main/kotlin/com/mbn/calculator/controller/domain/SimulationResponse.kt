package com.mbn.calculator.controller.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

data class SimulationResponse(
        val id: String,
        val installmentNumber: Int,
        val interest: BigDecimal,
        val principal: BigDecimal,
        val amount: BigDecimal,
        val timestamp: LocalDateTime
)
