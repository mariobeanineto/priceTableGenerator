package com.mbn.calculator.domain.repository

import com.mbn.calculator.domain.service.Client
import com.mbn.calculator.domain.service.PriceTable
import com.mbn.calculator.domain.service.Simulation
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("simulations")
data class SimulationMongo(
        @Id
        val id: String,
        val installmentNumber: Int,
        val interestRate: BigDecimal,
        val client: Client,
        val priceTableList: List<PriceTable>,
        val timestamp: LocalDateTime
) {
    companion object {
        fun from(simulation: Simulation): SimulationMongo {
            return SimulationMongo(
                    id = simulation.id,
                    installmentNumber = simulation.installmentNumber,
                    interestRate = simulation.interestRate,
                    timestamp = simulation.timestamp,
                    priceTableList = simulation.priceTableList,
                    client = simulation.client
            )
        }
    }
}