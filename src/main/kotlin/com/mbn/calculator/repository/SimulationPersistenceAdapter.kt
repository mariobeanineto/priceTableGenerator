package com.mbn.calculator.repository

import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.domain.repository.SimulationMongo
import com.mbn.calculator.interfaces.SimulationPersistenceInterface
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationPersistenceAdapter(
        val repositories: List<SimulationRepositoryInterface>
) : SimulationPersistenceInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        repositories.forEach {
            it.saveSimulation(simulation)
        }
    }
}