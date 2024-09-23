package com.mbn.calculator.implementation.concrete.business

import com.mbn.calculator.implementation.concrete.business.InstallmentComponent
import com.mbn.calculator.implementation.concrete.exceptions.InterestException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.math.RoundingMode

@ExtendWith(MockitoExtension::class)
class InstallmentComponentTest {

    @InjectMocks
    lateinit var installmentComponent: InstallmentComponent

    @Test
    fun `when given an present value amount should return the amount of the installments`() {
        val presentValue = BigDecimal("1000")
        val installments = 12
        val interestRate = BigDecimal("0.05")

        val result = installmentComponent.getInstallmentAmount(presentValue, installments, interestRate)

        assertEquals(BigDecimal("112.82"), result)
    }
}