package com.mbn.calculator.business.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Simulation(
        val id: String,
        val installmentNumber: Int,
        val interestRate: BigDecimal,
        val client: Client,
        val priceTableList: List<PriceTable>,
        val timestamp: LocalDateTime = LocalDateTime.now()
)
