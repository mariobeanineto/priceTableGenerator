package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation
import org.springframework.stereotype.Repository

@Repository
class SimulationMySqlRepository: SimulationRepositoryInterface {
    override fun saveSimulation(simulation: Simulation) {
        print("mysql")
    }
}