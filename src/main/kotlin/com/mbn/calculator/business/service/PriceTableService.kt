package com.mbn.calculator.business.service

import com.mbn.calculator.business.domain.PriceTable
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class PriceTableService(
        val installmentService: InstallmentService
) {
    fun getPriceTable(presentValueAmount: BigDecimal, installments: Int, interestRate: BigDecimal): List<PriceTable> {
        val installmentAmount = installmentService.getInstallmentAmount(presentValueAmount, installments, interestRate)
        val priceTableList = mutableListOf<PriceTable>()
        var amountLeft = presentValueAmount
        for (installmentNumber in 1..installments) {
            val interestAmount = getInterestAmount(amountLeft, interestRate)
            val principal = installmentAmount - interestAmount
            priceTableList.add(
                    PriceTable(
                            installmentNumber = installmentNumber,
                            amount = installmentAmount,
                            principal = principal,
                            interest = interestAmount
                    )
            )
            amountLeft -= principal
        }
        return priceTableList
    }

    private fun getInterestAmount(amount: BigDecimal, interestRate: BigDecimal): BigDecimal {
        return amount.multiply(interestRate).setScale(2, RoundingMode.HALF_EVEN)
    }
}