package com.mbn.calculator.service

import com.mbn.calculator.domain.service.Client
import com.mbn.calculator.domain.service.PriceTable
import com.mbn.calculator.domain.service.Simulation
import com.mbn.calculator.interfaces.InterestRateInterface
import com.mbn.calculator.interfaces.SimulationRepositoryInterface
import com.mbn.calculator.interfaces.SimulationServiceInterface
import kotlinx.coroutines.*
import org.springframework.cglib.proxy.Dispatcher
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
    @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
    override fun createSimulation(presentValueAmount: BigDecimal, installmentList: List<Int>, documentNumber: String, name: String): Simulation {
        val client = Client(documentNumber = documentNumber, name = name)
        val id = UUID.randomUUID()
        val interestRate = getInterestRate()
        val unityInterestRate = interestRate.divide(BigDecimal(100), 6, RoundingMode.HALF_EVEN)
        val deferredPriceTableList = mutableListOf<Deferred<PriceTable>>()
        val priceTableList = mutableListOf<PriceTable>()
        runBlocking {
            withContext(Dispatchers.Default) {
                installmentList.sorted().forEach { installment ->
                    deferredPriceTableList.add(async {
                        priceTableService.getPriceTable(presentValueAmount, installment, unityInterestRate)
                    })
                }
            }
            deferredPriceTableList.awaitAll()
        }

        deferredPriceTableList.forEach {
            priceTableList.add(it.getCompleted())
        }

        val simulation = Simulation(
                id = id.toString(),
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