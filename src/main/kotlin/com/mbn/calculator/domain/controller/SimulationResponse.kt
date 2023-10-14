package com.mbn.calculator.domain.controller

import com.mbn.calculator.domain.service.PriceTable
import com.mbn.calculator.domain.service.Simulation
import java.math.BigDecimal
import java.time.LocalDateTime

data class SimulationResponse(
        val id: String,
        val installmentNumber: Int,
        val interestRate: BigDecimal,
        val timestamp: LocalDateTime,
        val priceTableList: List<PriceTable>
) {
    companion object {
        fun from(simulation: Simulation): SimulationResponse {
            return SimulationResponse(
                    id = simulation.id,
                    installmentNumber = simulation.installmentNumber,
                    interestRate = simulation.interestRate,
                    timestamp = simulation.timestamp,
                    priceTableList = simulation.priceTableList
            )
        }
    }
}


