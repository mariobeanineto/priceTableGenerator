package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import com.mbn.calculator.interfaces.SimulationPersistenceInterface
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Profile("!mock")
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