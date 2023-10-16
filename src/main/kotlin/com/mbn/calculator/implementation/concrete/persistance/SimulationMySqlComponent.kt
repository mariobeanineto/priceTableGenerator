package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Simulation
import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.SimulationMySql
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Profile("mysql")
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