package com.mbn.calculator.controller.domain

import com.mbn.calculator.business.domain.PriceTable
import com.mbn.calculator.business.domain.Simulation
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


