package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Simulation

interface SimulationPersistenceInterface {
    suspend fun saveSimulation(simulation: Simulation)
}