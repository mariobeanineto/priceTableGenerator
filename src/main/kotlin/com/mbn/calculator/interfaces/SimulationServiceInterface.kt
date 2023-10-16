package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import java.math.BigDecimal

interface SimulationServiceInterface {
    fun createSimulation(presentValueAmount: BigDecimal, installmentList: List<Int>, documentNumber: String, name: String): Simulation
}