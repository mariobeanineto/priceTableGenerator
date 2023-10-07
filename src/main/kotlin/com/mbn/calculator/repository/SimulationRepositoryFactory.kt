package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation
import org.springframework.stereotype.Repository

@Repository
class SimulationRepositoryFactory(
        val simulationRepositoryList: List<SimulationRepositoryInterface>
): SimulationRepositoryInterface {
    override fun saveSimulation(simulation: Simulation) {
        simulationRepositoryList.forEach {
            it.saveSimulation(simulation)
        }
    }
}