package com.mbn.calculator.implementation.concrete.domain.business

import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMongo
import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMySql
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

    companion object {
        fun from(priceTableMongo: PriceTableMongo): PriceTable {
            return PriceTable(
                    installments = priceTableMongo.installments,
                    installmentList = priceTableMongo.installmentList.map { Installment.from(it) }.sortedBy { it.installmentNumber }
            )
        }

        fun from(priceTableMySql: PriceTableMySql): PriceTable {
            return PriceTable(
                    installments = priceTableMySql.installments,
                    installmentList = priceTableMySql.installmentList.map { Installment.from(it) }.sortedBy { it.installmentNumber }
            )
        }
    }
}