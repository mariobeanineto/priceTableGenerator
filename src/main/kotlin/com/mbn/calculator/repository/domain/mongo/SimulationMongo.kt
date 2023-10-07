package com.mbn.calculator.repository.domain.mongo

import com.mbn.calculator.business.domain.Client
import com.mbn.calculator.business.domain.PriceTable
import com.mbn.calculator.business.domain.Simulation
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