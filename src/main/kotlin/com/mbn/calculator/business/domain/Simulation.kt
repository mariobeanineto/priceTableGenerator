package com.mbn.calculator.business.domain

import java.math.BigDecimal

data class Simulation(
        val installmentNumber: Int,
        val interestRate: BigDecimal,
        val client: Client,
        val priceTable: PriceTable
)
