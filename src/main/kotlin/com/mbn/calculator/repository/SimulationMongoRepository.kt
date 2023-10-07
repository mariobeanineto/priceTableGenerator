package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation
import org.springframework.stereotype.Repository

@Repository
class SimulationMongoRepository: SimulationRepositoryInterface {
    override fun saveSimulation(simulation: Simulation) {
        print("mongo")
    }
}