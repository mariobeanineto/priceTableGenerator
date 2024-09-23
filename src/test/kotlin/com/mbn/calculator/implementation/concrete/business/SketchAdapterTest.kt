package com.mbn.calculator.implementation.concrete.business

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.PersistenceException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.InterestRateInterface
import com.mbn.calculator.interfaces.MetricsInterface
import com.mbn.calculator.interfaces.SketchPersistenceInterface
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class SketchAdapterTest {

    @Mock
    private lateinit var interestRateInterface: InterestRateInterface

    @Mock
    private lateinit var sketchPersistenceInterface: SketchPersistenceInterface

    @Mock
    private lateinit var priceTableComponent: PriceTableComponent

    @Mock
    private lateinit var metricsInterface: MetricsInterface

    @InjectMocks
    private lateinit var sketchAdapter: SketchAdapter


    @Test
    fun `test createSketch`() = runBlocking {
        val presentValueAmount = BigDecimal("1000")
        val installmentList = listOf(6, 12, 18)
        val documentNumber = "123456789"
        val name = "John Doe"

        whenever(interestRateInterface.getPercentageInterestRate()).thenReturn(BigDecimal("5.0"))


        whenever(sketchPersistenceInterface.saveSketch(any())).thenAnswer {
            it.arguments[0] as Sketch
        }

        var sketchCount = 0
        whenever(metricsInterface.addSketch()).thenAnswer { sketchCount++ }

        val sketch = sketchAdapter.createSketch(presentValueAmount, installmentList, documentNumber, name)

        assertEquals(documentNumber, sketch.client.documentNumber)
        assertEquals(name, sketch.client.name)
        assertEquals(3, sketch.priceTableList.size)
        assertEquals(BigDecimal("5.0"), sketch.interestRate)
        assertEquals(1, sketchCount)
    }

    @Test
    fun `test createSketch persistence exception`(): Unit = runBlocking {
        whenever(interestRateInterface.getPercentageInterestRate()).thenReturn(BigDecimal("5.0"))

        whenever(sketchPersistenceInterface.saveSketch(any())).thenThrow(PersistenceException("Persistence error"))

        assertThrows<SketchNotFoundException> {
            sketchAdapter.createSketch(BigDecimal("1000"), listOf(6, 12, 18), "123456789", "John Doe")
        }
    }

    @Test
    fun `test getSketch sketch not found`() {
        whenever(sketchPersistenceInterface.getSketch(any())).thenThrow(SketchNotFoundException("nonexistent_id"))

        assertThrows<SketchNotFoundException> {
            sketchAdapter.getSketch("nonexistent_id")
        }
    }

    @Test
    fun `should create sketch with empty installment list`() = runBlocking {
        // Arrange
        val presentValueAmount = BigDecimal("1000")
        val installmentList = emptyList<Int>()
        val documentNumber = "123456789"
        val name = "John Doe"
        whenever(interestRateInterface.getPercentageInterestRate()).thenReturn(BigDecimal("5.0"))
        whenever(sketchPersistenceInterface.saveSketch(any())).thenAnswer { it.arguments[0] as Sketch }

        val sketch = sketchAdapter.createSketch(presentValueAmount, installmentList, documentNumber, name)

        assertTrue(sketch.priceTableList.isEmpty())
    }
}

