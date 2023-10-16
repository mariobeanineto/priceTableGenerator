package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.persistance.SimulationMongo
import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Profile("mongo")
@Repository
class SimulationMongoComponent(
        val simulationMongoRepository: SimulationMongoRepository
) : SimulationRepositoryInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        simulationMongoRepository.save(SimulationMongo.from(simulation))
    }
}