package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMySql
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.Optional

@Profile("mysql")
@Repository
class SketchMySqlComponent(
        val sketchMySqlRepository: SketchMySqlRepository,
        val clientMySqlRepositoryInterface: ClientMySqlRepositoryInterface,
        val priceTableMySqlRepository: PriceTableMySqlRepository,
        val installmentMySqlRepository: InstallmentMySqlRepository
) : SketchRepositoryInterface {
    override suspend fun saveSketch(sketch: Sketch) {
        val clientMySql = clientMySqlRepositoryInterface.save(ClientMySql.from(sketch.client))
        val sketchMySql = sketchMySqlRepository.save(SketchMySql.from(sketch, clientMySql))
        val priceTableList = priceTableMySqlRepository.saveAll(sketch.priceTableList.map { PriceTableMySql.from(it, sketchMySql) })
        sketch.priceTableList.forEach { priceTable ->
            installmentMySqlRepository.saveAll(priceTable.installmentList.map { InstallmentMySql.from(it, priceTableList, priceTable.installments) })
        }
    }

    override fun getSketch(id: String): Optional<Sketch> {
        return try {
            Optional.of(Sketch.from(sketchMySqlRepository.findById(id).get()))
        } catch(e: NoSuchElementException) {
            Optional.empty<Sketch>()
        }
    }
}