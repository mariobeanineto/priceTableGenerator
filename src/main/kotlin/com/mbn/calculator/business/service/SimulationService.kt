package com.mbn.calculator.business.service

import com.mbn.calculator.business.domain.PriceTable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class SimulationService(
        val rateService: RateService,
        val priceTableService: PriceTableService
) {
    fun createSimulation(presentValueAmount: BigDecimal, installments: Int): List<PriceTable> {
        return priceTableService.getPriceTable(presentValueAmount, installments, getInterestRate())
    }

    private fun getInterestRate() = rateService.getSelicRate().divide(BigDecimal(100), 6, RoundingMode.HALF_EVEN)
}