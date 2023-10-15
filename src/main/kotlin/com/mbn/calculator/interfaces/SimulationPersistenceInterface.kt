package com.mbn.calculator.interfaces

import com.mbn.calculator.domain.service.Simulation

interface SimulationPersistenceInterface {
    suspend fun saveSimulation(simulation: Simulation)
}