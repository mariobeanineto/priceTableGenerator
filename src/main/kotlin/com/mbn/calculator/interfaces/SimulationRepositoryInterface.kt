package com.mbn.calculator.interfaces

import com.mbn.calculator.domain.service.Simulation

interface SimulationRepositoryInterface {
    suspend fun saveSimulation(simulation: Simulation)
}