package com.mbn.calculator.domain.repository

import com.mbn.calculator.domain.service.Client
import com.mbn.calculator.domain.service.PriceTable
import com.mbn.calculator.domain.service.Simulation
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
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
@Table(name = "simulation")
class SimulationMySql(
        @Id
        val id: String = "",
        val installmentNumber: Int = 0,
        val interestRate: BigDecimal = BigDecimal.ZERO,
        val timestamp: LocalDateTime = LocalDateTime.now()
) {
    @ManyToOne
    var clientMySql: ClientMySql = ClientMySql()

    companion object {
        fun from(simulation: Simulation): SimulationMySql {
            return SimulationMySql(
                    id = simulation.id,
                    installmentNumber = simulation.installmentNumber,
                    interestRate = simulation.interestRate,
                    timestamp = simulation.timestamp
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
        val amount: BigDecimal = BigDecimal.ZERO
) {
    @ManyToOne
    var simulation: SimulationMySql = SimulationMySql()

    companion object {
        fun from(priceTable: PriceTable): InstallmentMySql {
            return InstallmentMySql(
                    installmentNumber = priceTable.installmentNumber,
                    interest = priceTable.interest,
                    principal = priceTable.principal,
                    amount = priceTable.amount
            )
        }
    }
}

