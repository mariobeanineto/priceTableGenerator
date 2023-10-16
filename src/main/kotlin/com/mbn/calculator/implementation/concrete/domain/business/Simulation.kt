package com.mbn.calculator.implementation.concrete.domain.business

import java.math.BigDecimal
import java.time.LocalDateTime

data class Simulation(
        val id: String,
        val interestRate: BigDecimal,
        val client: Client,
        val priceTableList: List<PriceTable>,
        val timestamp: LocalDateTime = LocalDateTime.now()
)
