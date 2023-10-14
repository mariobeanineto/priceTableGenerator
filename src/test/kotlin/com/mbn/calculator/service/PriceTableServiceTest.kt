package com.mbn.calculator.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import java.math.RoundingMode

@ExtendWith(MockitoExtension::class)
class PriceTableServiceTest {

    @Mock
    lateinit var installmentService: InstallmentService

    @InjectMocks
    lateinit var priceTableService: PriceTableService

    @Test
    fun `when given an present value amount should return the amount of the installments`() {
        whenever(installmentService.getInstallmentAmount(any(), any(), any())).doReturn(2750.40.toBigDecimal())
        val response = priceTableService.getPriceTable(30000.toBigDecimal(), 12, 0.015.toBigDecimal())


        Assertions.assertEquals(response[0].interest, 450.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[0].principal, 2300.4.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[1].interest, 415.49.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[1].principal, 2334.91.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[2].interest, 380.47.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[2].principal, 2369.93.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[3].interest, 344.92.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[3].principal, 2405.48.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[4].interest, 308.84.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[4].principal, 2441.56.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[5].interest, 272.22.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[5].principal, 2478.18.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[6].interest, 235.04.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[6].principal, 2515.36.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[7].interest, 197.31.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[7].principal, 2553.09.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[8].interest, 159.02.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[8].principal, 2591.38.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[9].interest, 120.15.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[9].principal, 2630.25.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[10].interest, 80.69.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[10].principal, 2669.71.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))

        Assertions.assertEquals(response[11].interest, 40.65.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
        Assertions.assertEquals(response[11].principal, 2709.75.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN))
    }
}