package com.mbn.calculator.repository.interfaces

import com.mbn.calculator.business.domain.Simulation

interface SimulationRepositoryInterface {
    fun saveSimulation(simulation: Simulation)
}