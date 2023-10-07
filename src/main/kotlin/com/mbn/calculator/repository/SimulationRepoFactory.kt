package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation
import com.mbn.calculator.repository.domain.mongo.SimulationMongo
import com.mbn.calculator.repository.interfaces.SimulationMongoRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationRepoFactory(
        val simulationMongoRepositoryInterface: SimulationMongoRepositoryInterface,
        val simulationMySqlRepository: SimulationMySqlRepository
) {
    fun saveSimulation(simulation: Simulation) {
        simulationMongoRepositoryInterface.save(SimulationMongo.from(simulation))
        simulationMySqlRepository.saveSimulation(simulation)
    }
}