package com.mbn.calculator.implementation.concrete.domain.request

import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import java.math.BigDecimal
import java.time.LocalDateTime

data class SketchResponse(
        val id: String,
        val interestRate: BigDecimal,
        val timestamp: LocalDateTime,
        val priceTableList: List<PriceTableResponse>
) {
    companion object {
        fun from(sketch: Sketch): SketchResponse {
            return SketchResponse(
                    id = sketch.id,
                    interestRate = sketch.interestRate,
                    timestamp = sketch.timestamp,
                    priceTableList = sketch.priceTableList.map { PriceTableResponse.from(it) }
            )
        }
    }
}

data class PriceTableResponse(
        val installments: Int,
        val totalAmount: BigDecimal,
        val totalInterest: BigDecimal,
        val installmentList: List<InstallmentResponse>
) {
    companion object {
        fun from(priceTable: PriceTable): PriceTableResponse {
            return PriceTableResponse(
                    installments = priceTable.installments,
                    installmentList = priceTable.installmentList.map { InstallmentResponse.from(it) },
                    totalAmount = priceTable.totalAmount,
                    totalInterest = priceTable.totalInterest
            )
        }
    }
}

data class InstallmentResponse(
        val installmentNumber: Int,
        val interest: BigDecimal,
        val principal: BigDecimal,
        val amount: BigDecimal
) {
    companion object {
        fun from(installment: Installment): InstallmentResponse {
            return InstallmentResponse(
                    installmentNumber = installment.installmentNumber,
                    interest = installment.interest,
                    principal = installment.principal,
                    amount = installment.amount
            )
        }
    }
}


