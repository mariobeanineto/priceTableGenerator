package com.mbn.calculator.domain.service

import java.math.BigDecimal

data class PriceTable(
        val installmentNumber: Int,
        val interest: BigDecimal,
        val principal: BigDecimal,
        val amount: BigDecimal
)