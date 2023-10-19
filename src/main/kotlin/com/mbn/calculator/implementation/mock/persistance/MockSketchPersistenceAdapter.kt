package com.mbn.calculator.implementation.mock.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.interfaces.SketchPersistenceInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Profile("mock")
@Repository
class MockSketchPersistenceAdapter : SketchPersistenceInterface {
    override suspend fun saveSketch(sketch: Sketch) {
        print("mocked persistence")
    }

    override fun getSketch(id: String): Sketch {
        return Sketch(id, BigDecimal.TEN, Client("", ""), emptyList())
    }
}