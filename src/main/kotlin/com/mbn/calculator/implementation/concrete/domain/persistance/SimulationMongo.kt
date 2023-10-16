package com.mbn.calculator.implementation.concrete.domain.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("simulations")
data class SimulationMongo(
        @Id
        val id: String,
        val interestRate: BigDecimal,
        val client: Client,
        val priceTableList: List<PriceTableMongo>,
        val timestamp: LocalDateTime
) {
    companion object {
        fun from(simulation: Simulation): SimulationMongo {
            return SimulationMongo(
                    id = simulation.id,
                    interestRate = simulation.interestRate,
                    timestamp = simulation.timestamp,
                    priceTableList = simulation.priceTableList.map { PriceTableMongo.from(it) },
                    client = simulation.client
            )
        }
    }
}

data class PriceTableMongo(
        val installments: Int,
        val totalAmount: BigDecimal,
        val totalInterest: BigDecimal,
        val installmentList: List<InstallmentMongo>
) {
    companion object {
        fun from(priceTable: PriceTable): PriceTableMongo {
            return PriceTableMongo(
                    installments = priceTable.installments,
                    installmentList = priceTable.installmentList.map { InstallmentMongo.from(it) },
                    totalAmount = priceTable.totalAmount,
                    totalInterest = priceTable.totalInterest
            )
        }
    }
}

data class InstallmentMongo(
        val installmentNumber: Int,
        val interest: BigDecimal,
        val principal: BigDecimal,
        val amount: BigDecimal
) {
    companion object {
        fun from(installment: Installment): InstallmentMongo {
            return InstallmentMongo(
                    installmentNumber = installment.installmentNumber,
                    interest = installment.interest,
                    principal = installment.principal,
                    amount = installment.amount
            )
        }
    }
}