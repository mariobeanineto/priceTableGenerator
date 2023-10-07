package com.mbn.calculator.business.service

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class InstallmentService {
    fun getInstallmentAmount(presentValueAmount: BigDecimal, installments: Int, interestRate: BigDecimal): BigDecimal {
        val interestPowered = getInterestPowered(interestRate, installments)
        val factor = getNumerator(interestPowered, interestRate).divide(getDenominator(interestPowered), 6, RoundingMode.HALF_UP)
        return presentValueAmount.multiply(factor).setScale(2, RoundingMode.HALF_EVEN)
    }

    private fun getDenominator(interestPowered: BigDecimal): BigDecimal {
        return interestPowered.minus(BigDecimal.ONE)
    }

    private fun getNumerator(interestPowered: BigDecimal, unitaryInterest: BigDecimal): BigDecimal {
        return interestPowered.multiply(unitaryInterest)
    }

    private fun getInterestPowered(unitaryInterest: BigDecimal, installments: Int): BigDecimal {
        return unitaryInterest.add(BigDecimal.ONE).pow(installments)
    }
}