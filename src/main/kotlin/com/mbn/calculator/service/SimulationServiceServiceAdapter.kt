package com.mbn.calculator.service

import com.mbn.calculator.domain.service.Client
import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.interfaces.InterestRateInterface
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import com.mbn.calculator.interfaces.SimulationServiceInterface
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@Service
class SimulationServiceServiceAdapter(
        val interestRateInterface: InterestRateInterface,
        val priceTableService: PriceTableService,
        val simulationRepositoryInterface: SimulationRepositoryInterface
) : SimulationServiceInterface {
    @OptIn(DelicateCoroutinesApi::class)
    override fun createSimulation(presentValueAmount: BigDecimal, installments: Int, documentNumber: String, name: String): Simulation {
        val client = Client(documentNumber = documentNumber, name = name)
        val id = UUID.randomUUID()
        val interestRate = getInterestRate()
        val unityInterestRate = interestRate.divide(BigDecimal(100), 6, RoundingMode.HALF_EVEN)
        val priceTableList = priceTableService.getPriceTable(presentValueAmount, installments, unityInterestRate)
        val simulation = Simulation(
                id = id.toString(),
                installmentNumber = installments,
                interestRate = interestRate,
                client = client,
                priceTableList = priceTableList
        )
        GlobalScope.launch {
            simulationRepositoryInterface.saveSimulation(simulation)
        }
        return simulation
    }

    private fun getInterestRate() = interestRateInterface.getInterestRate()
}