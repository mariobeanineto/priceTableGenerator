package com.mbn.calculator.repository

import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.domain.repository.ClientMySql
import com.mbn.calculator.domain.repository.InstallmentMySql
import com.mbn.calculator.domain.repository.PriceTableMySql
import com.mbn.calculator.domain.repository.SimulationMySql
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.stereotype.Repository

@Repository
class SimulationMySqlComponent(
        val simulationMySqlRepository: SimulationMySqlRepository,
        val clientMySqlRepositoryInterface: ClientMySqlRepositoryInterface,
        val priceTableMySqlRepository: PriceTableMySqlRepository,
        val installmentMySqlRepository: InstallmentMySqlRepository
) : SimulationRepositoryInterface {
    override suspend fun saveSimulation(simulation: Simulation) {
        val clientMySql = clientMySqlRepositoryInterface.save(ClientMySql.from(simulation.client))
        val simulationMySql = simulationMySqlRepository.save(SimulationMySql.from(simulation, clientMySql))
        val priceTableList = priceTableMySqlRepository.saveAll(simulation.priceTableList.map { PriceTableMySql.from(it, simulationMySql) })
        simulation.priceTableList.forEach { priceTable ->
            installmentMySqlRepository.saveAll(priceTable.installmentList.map { InstallmentMySql.from(it, priceTableList, priceTable.installments) })
        }
    }
}