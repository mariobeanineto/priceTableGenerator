package com.mbn.calculator.business.service

import com.mbn.calculator.business.service.RateService
import com.mbn.calculator.business.service.InstallmentService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.math.RoundingMode

@ExtendWith(MockitoExtension::class)
class InstallmentServiceTest {

    @InjectMocks
    lateinit var installmentService: InstallmentService

    @Test
    fun `when given an present value amount should return the amount of the installments`() {
        Assertions.assertEquals(installmentService.getInstallmentAmount(BigDecimal(30000), 12, BigDecimal(0.015)).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal(2750.40).setScale(2, RoundingMode.HALF_EVEN))
    }
}