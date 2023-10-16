package com.mbn.calculator.implementation.mock.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import com.mbn.calculator.interfaces.SimulationPersistenceInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Profile("mock")
@Repository
class MockSimulationPersistenceAdapter : SimulationPersistenceInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        print("mocked persistence")
    }
}