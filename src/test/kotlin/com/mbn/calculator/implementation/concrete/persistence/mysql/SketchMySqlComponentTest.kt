package com.mbn.calculator.implementation.concrete.persistence.mysql

import com.mbn.calculator.implementation.concrete.domain.business.Client
import com.mbn.calculator.implementation.concrete.domain.business.Installment
import com.mbn.calculator.implementation.concrete.domain.business.PriceTable
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.InstallmentMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.PriceTableMySql
import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMySql
import com.mbn.calculator.implementation.concrete.exceptions.MySqlException
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.dao.DataIntegrityViolationException
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class SketchMySqlComponentTest {

    @Mock
    lateinit var clientMySqlRepository: ClientMySqlRepositoryInterface

    @Mock
    lateinit var priceTableMySqlRepository: PriceTableMySqlRepository

    @Mock
    lateinit var installmentMySqlRepository: InstallmentMySqlRepository

    @Mock
    lateinit var sketchMySqlRepository: SketchMySqlRepository

    @InjectMocks
    lateinit var sketchMySqlComponent: SketchMySqlComponent

    @Test
    fun `saveSketch - success`(): Unit = runBlocking {
        whenever(clientMySqlRepository.save(any<ClientMySql>())).thenReturn(ClientMySql())
        whenever(sketchMySqlRepository.save(any<SketchMySql>())).thenReturn(SketchMySql())
        whenever(priceTableMySqlRepository.saveAll(any<List<PriceTableMySql>>())).thenReturn(listOf(createPriceTableMySql()))
        whenever(installmentMySqlRepository.saveAll(any<List<InstallmentMySql>>())).thenReturn(listOf(InstallmentMySql()))

        sketchMySqlComponent.saveSketch(createSketch())

        verify(clientMySqlRepository).save(any())
        verify(sketchMySqlRepository).save(any())
        verify(priceTableMySqlRepository).saveAll((any<List<PriceTableMySql>>()))
        verify(installmentMySqlRepository).saveAll(any<List<InstallmentMySql>>())
    }

    @Test
    fun `getSketch - SketchNotFoundException`(): Unit = runBlocking {
        whenever(clientMySqlRepository.save(any<ClientMySql>())).thenThrow(DataIntegrityViolationException("error"))

        assertThrows<MySqlException> {
            sketchMySqlComponent.saveSketch(createSketch())
        }
    }

    private fun createSketch(): Sketch {
        val client = Client("123456789", "John Doe")

        val installment1 = Installment(1, BigDecimal("0.05"), BigDecimal("1000"), BigDecimal("1050"))

        val priceTable = PriceTable(3, listOf(installment1))

        return Sketch("123ABC", BigDecimal("0.1"), client, listOf(priceTable))
    }

    private fun createPriceTableMySql(): PriceTableMySql {
        val sketch = SketchMySql()
        val installment = InstallmentMySql()
        return PriceTableMySql(
                installments = 1,
                sketch = sketch,
                installmentList = listOf(installment)
        )
    }
}
