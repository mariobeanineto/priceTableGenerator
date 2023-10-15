package com.mbn.calculator.repository

import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.domain.repository.SimulationMongo
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationRepositoryAdapter(
        val simulationMongoRepository: SimulationMongoRepository,
        val simulationMySqlRepository: SimulationMySqlRepository
): SimulationRepositoryInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        simulationMongoRepository.save(SimulationMongo.from(simulation))
        simulationMySqlRepository.saveSimulation(simulation)
    }
}