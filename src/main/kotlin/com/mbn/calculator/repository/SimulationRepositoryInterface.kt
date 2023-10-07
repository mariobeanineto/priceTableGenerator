package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation

interface SimulationRepositoryInterface {
    fun saveSimulation(simulation: Simulation)
}