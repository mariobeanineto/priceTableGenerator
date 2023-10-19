package com.mbn.calculator.implementation.concrete.business

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.interfaces.InterestRateInterface
import com.mbn.calculator.interfaces.MetricsInterface
import com.mbn.calculator.interfaces.SketchPersistenceInterface
import com.mbn.calculator.interfaces.SketchServiceInterface
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@Service
class SketchAdapter(
        val interestRateInterface: InterestRateInterface,
        val priceTableComponent: PriceTableComponent,
        val sketchPersistenceInterface: SketchPersistenceInterface,
        val metricsInterface: MetricsInterface
) : SketchServiceInterface {
    override fun createSketch(presentValueAmount: BigDecimal, installmentList: List<Int>, documentNumber: String, name: String): Sketch {
        val client = Client(documentNumber = documentNumber, name = name)
        val id = UUID.randomUUID()
        val percentageInterestRate = getPercentageInterestRate()
        val interestRate = percentageInterestRate.divide(BigDecimal(100), 6, RoundingMode.HALF_EVEN)

        val sketch = Sketch(
                id = id.toString(),
                interestRate = percentageInterestRate,
                client = client,
                priceTableList = createPriceTableList(
                        installmentList,
                        presentValueAmount,
                        interestRate)
        )
        saveSketch(sketch)
        addRequestToMetric()
        return sketch
    }

    override fun getSketch(id: String): Sketch {
        return sketchPersistenceInterface.getSketch(id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun createPriceTableList(
            installmentList: List<Int>,
            presentValueAmount: BigDecimal,
            interestRate: BigDecimal
    ): List<PriceTable> {
        val deferredPriceTableList = mutableListOf<Deferred<PriceTable>>()
        val priceTableList = mutableListOf<PriceTable>()
        runBlocking {
            withContext(Dispatchers.Default) {
                installmentList.sorted().forEach { installment ->
                    deferredPriceTableList.add(async {
                        priceTableComponent.getPriceTable(presentValueAmount, installment, interestRate)
                    })
                }
            }
            deferredPriceTableList.awaitAll()
            deferredPriceTableList.forEach {
                priceTableList.add(it.getCompleted())
            }
        }
        return priceTableList
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveSketch(sketch: Sketch) {
        GlobalScope.launch {
            sketchPersistenceInterface.saveSketch(sketch)
        }
    }

    private fun addRequestToMetric() {
        metricsInterface.addSketch()
    }

    private fun getPercentageInterestRate() = interestRateInterface.getPercentageInterestRate()
}