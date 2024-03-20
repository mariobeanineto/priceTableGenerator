package com.mbn.calculator.implementation.concrete.domain.business

import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMongo
import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMySql
import java.math.BigDecimal

data class Installment(
    val installmentNumber: Int,
    val interest: BigDecimal,
    val principal: BigDecimal,
    val amount: BigDecimal
) {
    companion object {
        fun from(installmentMongo: InstallmentMongo): Installment {
            return Installment(
                installmentNumber = installmentMongo.installmentNumber,
                interest = installmentMongo.interest,
                principal = installmentMongo.principal,
                amount = installmentMongo.amount
            )
        }

        fun from(installmentMySql: InstallmentMySql): Installment {
            return Installment(
                installmentNumber = installmentMySql.installmentNumber,
                interest = installmentMySql.interest,
                principal = installmentMySql.principal,
                amount = installmentMySql.amount
            )
        }
    }
}
