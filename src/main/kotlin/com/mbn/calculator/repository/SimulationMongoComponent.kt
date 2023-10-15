package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.SimulationMongo
import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationMongoComponent(
        val simulationMongoRepository: SimulationMongoRepository
) : SimulationRepositoryInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        simulationMongoRepository.save(SimulationMongo.from(simulation))
    }
}