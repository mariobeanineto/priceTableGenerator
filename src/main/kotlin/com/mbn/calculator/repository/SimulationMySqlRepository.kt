package com.mbn.calculator.repository

import com.mbn.calculator.business.domain.Simulation
import com.mbn.calculator.repository.domain.mysql.ClientMySql
import com.mbn.calculator.repository.domain.mysql.InstallmentMySql
import com.mbn.calculator.repository.domain.mysql.SimulationMySql
import com.mbn.calculator.repository.interfaces.ClientMySqlRepositoryInterface
import com.mbn.calculator.repository.interfaces.InstallmentMySqlRepositoryInterface
import com.mbn.calculator.repository.interfaces.SimulationMySqlRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationMySqlRepository(
        val simulationMySqlRepositoryInterface: SimulationMySqlRepositoryInterface,
        val clientMySqlRepositoryInterface: ClientMySqlRepositoryInterface,
        val installmentMySqlRepositoryInterface: InstallmentMySqlRepositoryInterface
) {
    fun saveSimulation(simulation: Simulation) {
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