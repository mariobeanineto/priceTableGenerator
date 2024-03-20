package com.mbn.calculator.implementation.concrete.domain.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "client")
class ClientMySql(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val documentNumber: String = "",
    val name: String = ""
) {
    companion object {
        fun from(client: Client): ClientMySql {
            return ClientMySql(
                documentNumber = client.documentNumber,
                name = client.name
            )
        }
    }
}

@Entity
@Table(name = "sketch")
class SketchMySql(
    @Id
    val id: String = "",
    val interestRate: BigDecimal = BigDecimal.ZERO,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    var clientMySql: ClientMySql = ClientMySql(),
    @OneToMany(mappedBy = "sketch")
    var priceTableList: List<PriceTableMySql> = emptyList()
) {


    companion object {
        fun from(sketch: Sketch, clientMySql: ClientMySql): SketchMySql {
            return SketchMySql(
                id = sketch.id,
                interestRate = sketch.interestRate,
                timestamp = sketch.timestamp,
                clientMySql = clientMySql
            )
        }
    }
}

@Entity
@Table(name = "price_table")
class PriceTableMySql(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val installments: Int = 0,
    @ManyToOne
    var sketch: SketchMySql = SketchMySql(),
    @OneToMany(mappedBy = "priceTable")
    var installmentList: List<InstallmentMySql> = emptyList()
) {
    companion object {
        fun from(priceTable: PriceTable, sketchMySql: SketchMySql): PriceTableMySql {
            return PriceTableMySql(
                installments = priceTable.installments,
                sketch = sketchMySql
            )
        }
    }
}

@Entity
@Table(name = "installment")
class InstallmentMySql(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val installmentNumber: Int = 0,
    val interest: BigDecimal = BigDecimal.ZERO,
    val principal: BigDecimal = BigDecimal.ZERO,
    val amount: BigDecimal = BigDecimal.ZERO,
    @ManyToOne
    val priceTable: PriceTableMySql = PriceTableMySql()
) {

    companion object {
        fun from(
            installment: Installment,
            priceTable: MutableList<PriceTableMySql>,
            installments: Int
        ): InstallmentMySql {
            return InstallmentMySql(
                installmentNumber = installment.installmentNumber,
                interest = installment.interest,
                principal = installment.principal,
                amount = installment.amount,
                priceTable = priceTable.find { it.installments == installments }!!
            )
        }
    }
}

