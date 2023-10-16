package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Simulation

interface SimulationRepositoryInterface {
    suspend fun saveSimulation(simulation: Simulation)
}