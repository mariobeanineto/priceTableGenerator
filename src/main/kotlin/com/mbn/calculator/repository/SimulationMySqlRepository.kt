package com.mbn.calculator.repository

import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.domain.repository.ClientMySql
import com.mbn.calculator.domain.repository.InstallmentMySql
import com.mbn.calculator.domain.repository.SimulationMySql
import com.mbn.calculator.interfaces.ClientMySqlRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationMySqlRepository(
        val simulationMySqlRepositoryInterface: SimulationMySqlRepositoryInterface,
        val clientMySqlRepositoryInterface: ClientMySqlRepositoryInterface,
        val installmentMySqlRepositoryInterface: InstallmentMySqlRepositoryInterface
) {
    suspend fun saveSimulation(simulation: Simulation) {
        val clientMySql = clientMySqlRepositoryInterface.save(ClientMySql.from(simulation.client))
        val simulationMySql = SimulationMySql.from(simulation)
        simulationMySql.clientMySql = clientMySql
        simulationMySqlRepositoryInterface.save(simulationMySql)
        simulation.priceTableList.forEach {
            val installmentMySql = InstallmentMySql.from(it)
            installmentMySql.simulation = simulationMySql
            installmentMySqlRepositoryInterface.save(installmentMySql)
        }
    }
}