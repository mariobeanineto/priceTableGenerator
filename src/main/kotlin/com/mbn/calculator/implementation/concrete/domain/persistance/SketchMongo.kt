package com.mbn.calculator.implementation.concrete.domain.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("sketch")
data class SketchMongo(
    @Id
    val id: String,
    val interestRate: BigDecimal,
    val client: ClientMongo,
    val priceTableList: List<PriceTableMongo>,
    val timestamp: LocalDateTime
) {
    companion object {
        fun from(sketch: Sketch): SketchMongo {
            return SketchMongo(
                id = sketch.id,
                interestRate = sketch.interestRate,
                timestamp = sketch.timestamp,
                priceTableList = sketch.priceTableList.map { PriceTableMongo.from(it) },
                client = ClientMongo.from(sketch.client)
            )
        }
    }
}

data class ClientMongo(
    val documentNumber: String,
    val name: String
) {
    companion object {
        fun from(client: Client): ClientMongo {
            return ClientMongo(
                documentNumber = client.documentNumber,
                name = client.name
            )
        }
    }
}

data class PriceTableMongo(
    val installments: Int,
    val totalAmount: BigDecimal,
    val totalInterest: BigDecimal,
    val installmentList: List<InstallmentMongo>
) {
    companion object {
        fun from(priceTable: PriceTable): PriceTableMongo {
            return PriceTableMongo(
                installments = priceTable.installments,
                installmentList = priceTable.installmentList.map { InstallmentMongo.from(it) },
                totalAmount = priceTable.totalAmount,
                totalInterest = priceTable.totalInterest
            )
        }
    }
}

data class InstallmentMongo(
    val installmentNumber: Int,
    val interest: BigDecimal,
    val principal: BigDecimal,
    val amount: BigDecimal
) {
    companion object {
        fun from(installment: Installment): InstallmentMongo {
            return InstallmentMongo(
                installmentNumber = installment.installmentNumber,
                interest = installment.interest,
                principal = installment.principal,
                amount = installment.amount
            )
        }
    }
}