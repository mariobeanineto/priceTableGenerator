package com.mbn.calculator.implementation.concrete.business

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class PriceTableComponentTest {

    @Mock
    lateinit var installmentComponent: InstallmentComponent

    @InjectMocks
    lateinit var priceTableComponent: PriceTableComponent

    @Test
    fun `when given an present value amount should return the amount of the installments`() = runTest {
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).doReturn(2750.40.toBigDecimal())
        val response = priceTableComponent.getPriceTable(30000.toBigDecimal(), 12, 0.015.toBigDecimal())

        assertEquals(response.totalAmount, 33004.80.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.totalInterest, 3004.80.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[0].interest, 450.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[0].principal, 2300.4.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[1].interest, 415.49.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[1].principal, 2334.91.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[2].interest, 380.47.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[2].principal, 2369.93.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[3].interest, 344.92.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[3].principal, 2405.48.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[4].interest, 308.84.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[4].principal, 2441.56.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[5].interest, 272.22.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[5].principal, 2478.18.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[6].interest, 235.04.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[6].principal, 2515.36.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[7].interest, 197.31.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[7].principal, 2553.09.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[8].interest, 159.02.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[8].principal, 2591.38.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[9].interest, 120.15.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[9].principal, 2630.25.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[10].interest, 80.69.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[10].principal, 2669.71.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        assertEquals(response.installmentList[11].interest, 40.65.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        assertEquals(response.installmentList[11].principal, 2709.75.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
    }

    @Test
    fun `should return correct price table when interest rate is zero`() = runTest {
        val presentValue = BigDecimal("1000")
        val installments = 12
        val interestRate = BigDecimal("0.0")
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).thenReturn(BigDecimal("83.33"))

        val priceTable = priceTableComponent.getPriceTable(presentValue, installments, interestRate)

        assertEquals(12, priceTable.installments)
        assertEquals(12, priceTable.installmentList.size)
        assertEquals(BigDecimal("83.33"), priceTable.installmentList[0].amount)
        assertEquals(BigDecimal("0.00"), priceTable.installmentList[0].interest)
        assertEquals(BigDecimal("83.33"), priceTable.installmentList[0].principal)
    }

    @Test
    fun `should return correct price table for single installment`() = runTest {

        val presentValue = BigDecimal("1000")
        val installments = 1
        val interestRate = BigDecimal("0.05")
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).thenReturn(BigDecimal("1050.00"))

        val priceTable = priceTableComponent.getPriceTable(presentValue, installments, interestRate)

        assertEquals(1, priceTable.installments)
        assertEquals(1, priceTable.installmentList.size)
        assertEquals(BigDecimal("1050.00"), priceTable.installmentList[0].amount)
        assertEquals(BigDecimal("50.00"), priceTable.installmentList[0].interest)
        assertEquals(BigDecimal("1000.00"), priceTable.installmentList[0].principal)
    }

    @Test
    fun `should return zero installments when present value is zero`() = runTest {

        val presentValue = BigDecimal("0")
        val installments = 12
        val interestRate = BigDecimal("0.05")
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).thenReturn(BigDecimal("0.00"))

        val priceTable = priceTableComponent.getPriceTable(presentValue, installments, interestRate)

        assertEquals(12, priceTable.installments)
        assertEquals(12, priceTable.installmentList.size)
        assertEquals(BigDecimal("0.00"), priceTable.installmentList[0].amount)
        assertEquals(BigDecimal("0.00"), priceTable.installmentList[0].interest)
        assertEquals(BigDecimal("0.00"), priceTable.installmentList[0].principal)
    }

    @Test
    fun `should throw exception when installments is zero`() = runTest {

        val presentValue = BigDecimal("1000")
        val installments = 0
        val interestRate = BigDecimal("0.05")

        assertThrows<IllegalArgumentException> {
            priceTableComponent.getPriceTable(presentValue, installments, interestRate)
        }
    }

    @Test
    fun `should return correct price table for negative interest rate`() = runTest {
        val presentValue = BigDecimal("1000")
        val installments = 12
        val interestRate = BigDecimal("-0.05")
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).thenReturn(BigDecimal("79.17"))

        val priceTable = priceTableComponent.getPriceTable(presentValue, installments, interestRate)

        assertEquals(12, priceTable.installments)
        assertEquals(12, priceTable.installmentList.size)
        assertEquals(BigDecimal("79.17"), priceTable.installmentList[0].amount)
        assertEquals(BigDecimal("-50.00"), priceTable.installmentList[0].interest)
        assertEquals(BigDecimal("129.17"), priceTable.installmentList[0].principal)
    }

    @Test
    fun `should return correct price table for negative present value`() = runTest {
        val presentValue = BigDecimal("-1000")
        val installments = 12
        val interestRate = BigDecimal("0.05")

        assertThrows<IllegalArgumentException> {
            priceTableComponent.getPriceTable(
                presentValue,
                installments,
                interestRate
            )
        }

    }

    @Test
    fun `should return correct price table for fractional installment amounts`() = runTest {
        val presentValue = BigDecimal("1001")
        val installments = 12
        val interestRate = BigDecimal("0.05")
        whenever(installmentComponent.getInstallmentAmount(any(), any(), any())).thenReturn(BigDecimal("112.83"))

        val priceTable = priceTableComponent.getPriceTable(presentValue, installments, interestRate)

        assertEquals(12, priceTable.installments)
        assertEquals(12, priceTable.installmentList.size)
        assertEquals(BigDecimal("112.83"), priceTable.installmentList[0].amount)
        assertEquals(BigDecimal("50.05"), priceTable.installmentList[0].interest)
        assertEquals(BigDecimal("62.78"), priceTable.installmentList[0].principal)
    }
}