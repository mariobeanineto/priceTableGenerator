package com.mbn.calculator.implementation.concrete.business

import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.RoundingMode

@Component
class PriceTableComponent(
    val installmentComponent: InstallmentComponent
) {
    suspend fun getPriceTable(presentValueAmount: BigDecimal, installments: Int, interestRate: BigDecimal): PriceTable {
        doValidations(installments, presentValueAmount)
        val installmentAmount =
            installmentComponent.getInstallmentAmount(presentValueAmount, installments, interestRate)
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

    private fun doValidations(installments: Int, presentValueAmount: BigDecimal) {
        validatePresentValue(presentValueAmount)
        validateInstallmentNumber(installments)
    }

    private fun validatePresentValue(presentValueAmount: BigDecimal) {
        if (presentValueAmount < BigDecimal.ZERO) {
            throw IllegalArgumentException("Invalid presentValueAmount")
        }
    }

    private fun validateInstallmentNumber(installments: Int) {
        if (installments <= 0) {
            throw IllegalArgumentException("Invalid installment number")
        }
    }

    private fun getInterestAmount(amount: BigDecimal, interestRate: BigDecimal): BigDecimal {
        return amount.multiply(interestRate).setScale(2, RoundingMode.HALF_EVEN)
    }
}