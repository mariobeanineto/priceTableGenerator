package com.mbn.calculator.service

import com.mbn.calculator.domain.service.Installment
import com.mbn.calculator.domain.service.PriceTable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class PriceTableService(
        val installmentService: InstallmentService
) {
    suspend fun getPriceTable(presentValueAmount: BigDecimal, installments: Int, interestRate: BigDecimal): PriceTable {
        val installmentAmount = installmentService.getInstallmentAmount(presentValueAmount, installments, interestRate)
        val installmentList = mutableListOf<Installment>()
        var amountLeft = presentValueAmount
        for (installmentNumber in 1..installments) {
            val interestAmount = getInterestAmount(amountLeft, interestRate)
            val principal = installmentAmount - interestAmount
            installmentList.add(
                    Installment(
                            installmentNumber = installmentNumber,
                            amount = installmentAmount,
                            principal = principal,
                            interest = interestAmount
                    )
            )
            amountLeft -= principal
        }

        return PriceTable(
                installments = installments,
                installmentList = installmentList
        )
    }

    private fun getInterestAmount(amount: BigDecimal, interestRate: BigDecimal): BigDecimal {
        return amount.multiply(interestRate).setScale(2, RoundingMode.HALF_EVEN)
    }
}