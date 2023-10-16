package com.mbn.calculator.implementation.concrete.domain.business

import java.math.BigDecimal
import java.math.RoundingMode

data class PriceTable(
        val installments: Int,
        val installmentList: List<Installment>
) {

    lateinit var totalAmount: BigDecimal
    lateinit var totalInterest: BigDecimal
    init {
        with(this) {
            totalAmount = installmentList.sumOf { it.amount }.setScale(2, RoundingMode.HALF_EVEN)
            totalInterest = installmentList.sumOf { it.interest }.setScale(2, RoundingMode.HALF_EVEN)
        }
    }
}

data class Installment(
        val installmentNumber: Int,
        val interest: BigDecimal,
        val principal: BigDecimal,
        val amount: BigDecimal
)