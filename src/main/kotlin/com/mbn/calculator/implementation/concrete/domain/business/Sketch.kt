package com.mbn.calculator.implementation.concrete.domain.business

import com.mbn.calculator.implementation.concrete.domain.persistance.*
import java.math.BigDecimal
import java.time.LocalDateTime

data class Sketch(
    val id: String,
    val interestRate: BigDecimal,
    val client: Client,
    val priceTableList: List<PriceTable>,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun from(sketchMongo: SketchMongo): Sketch {
            return Sketch(
                id = sketchMongo.id,
                interestRate = sketchMongo.interestRate,
                client = Client.from(sketchMongo.client),
                priceTableList = sketchMongo.priceTableList.map { PriceTable.from(it) }.sortedBy { it.installments },
                timestamp = sketchMongo.timestamp
            )
        }

        fun from(sketchMySql: SketchMySql): Sketch {
            return Sketch(
                id = sketchMySql.id,
                interestRate = sketchMySql.interestRate,
                client = Client.from(sketchMySql.clientMySql),
                priceTableList = sketchMySql.priceTableList.map { PriceTable.from(it) }.sortedBy { it.installments },
                timestamp = sketchMySql.timestamp
            )
        }
    }
}


