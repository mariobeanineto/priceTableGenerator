package com.mbn.calculator.interfaces

import com.mbn.calculator.domain.service.Simulation
import java.math.BigDecimal

interface SimulationServiceInterface {
    fun createSimulation(presentValueAmount: BigDecimal, installments: Int, documentNumber: String, name: String): Simulation
}