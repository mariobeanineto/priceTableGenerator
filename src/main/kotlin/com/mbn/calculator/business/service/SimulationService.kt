package com.mbn.calculator.business.service

import com.mbn.calculator.business.domain.Client
import com.mbn.calculator.business.domain.Simulation
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@Service
class SimulationService(
        val rateService: RateService,
        val priceTableService: PriceTableService
) {
    fun createSimulation(presentValueAmount: BigDecimal, installments: Int, documentNumber: String, name: String): Simulation {
        val client = Client(documentNumber = documentNumber, name = name)
        val id = UUID.randomUUID()
        val interestRate = getInterestRate()
        val unityInterestRate = interestRate.divide(BigDecimal(100), 6, RoundingMode.HALF_EVEN)
        val priceTableList = priceTableService.getPriceTable(presentValueAmount, installments, unityInterestRate)
        return Simulation(
                id = id.toString(),
                installmentNumber = installments,
                interestRate = interestRate,
                client = client,
                priceTableList = priceTableList
        )
    }

    private fun getInterestRate() = rateService.getSelicRate()
}