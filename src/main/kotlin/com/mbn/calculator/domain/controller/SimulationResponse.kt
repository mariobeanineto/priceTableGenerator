package com.mbn.calculator.domain.controller

import com.mbn.calculator.domain.service.Installment
import com.mbn.calculator.domain.service.PriceTable
import com.mbn.calculator.domain.service.Simulation
import java.math.BigDecimal
import java.time.LocalDateTime

data class SimulationResponse(
        val id: String,
        val interestRate: BigDecimal,
        val timestamp: LocalDateTime,
        val priceTableList: List<PriceTableResponse>
) {
    companion object {
        fun from(simulation: Simulation): SimulationResponse {
            return SimulationResponse(
                    id = simulation.id,
                    interestRate = simulation.interestRate,
                    timestamp = simulation.timestamp,
                    priceTableList = simulation.priceTableList.map { PriceTableResponse.from(it) }
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


