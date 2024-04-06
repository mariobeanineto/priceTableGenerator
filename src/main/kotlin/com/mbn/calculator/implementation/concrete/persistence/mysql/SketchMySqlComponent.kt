package com.mbn.calculator.implementation.concrete.persistence.mysql

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMySql
import com.mbn.calculator.implementation.concrete.exceptions.MySqlException
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.*

@Profile("mysql")
@Repository
class SketchMySqlComponent(
        val sketchMySqlRepository: SketchMySqlRepository,
        val clientMySqlRepositoryInterface: ClientMySqlRepositoryInterface,
        val priceTableMySqlRepository: PriceTableMySqlRepository,
        val installmentMySqlRepository: InstallmentMySqlRepository
) : SketchRepositoryInterface {

    @Throws(MySqlException::class)
    override suspend fun saveSketch(sketch: Sketch) {
        try {
            val clientMySql = clientMySqlRepositoryInterface.save(ClientMySql.from(sketch.client))
            val sketchMySql = sketchMySqlRepository.save(SketchMySql.from(sketch, clientMySql))
            val priceTableList =
                    priceTableMySqlRepository.saveAll(sketch.priceTableList.map { PriceTableMySql.from(it, sketchMySql) })

            sketch.priceTableList.forEach { priceTable ->
                installmentMySqlRepository.saveAll(priceTable.installmentList.map {
                    InstallmentMySql.from(
                            it,
                            priceTableList,
                            priceTable.installments
                    )
                })
            }
        } catch (e: Exception) {
            throw MySqlException(e.message!!)
        }
    }

    override fun getSketch(id: String): Optional<Sketch> {
        return try {
            Optional.of(Sketch.from(sketchMySqlRepository.findById(id).get()))
        } catch (e: NoSuchElementException) {
            Optional.empty<Sketch>()
        }
    }
}