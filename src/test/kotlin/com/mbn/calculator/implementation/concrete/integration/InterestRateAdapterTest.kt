package com.mbn.calculator.implementation.concrete.integration

import com.mbn.calculator.implementation.concrete.domain.integration.SelicResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Value
import retrofit2.Call
import retrofit2.Response
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
class InterestRateAdapterTest {

    @Mock
    lateinit var bacenIntegration: BacenIntegration

    @Mock
    lateinit var call: Call<List<SelicResponse>>

    @Mock
    lateinit var response: Response<List<SelicResponse>>

    @InjectMocks
    lateinit var interestRateAdapter: InterestRateAdapter

    @Value("\${sketch.default.interest.rate}")
    private val defaultInterest = "5.0" // Mock default interest rate

    @BeforeEach
    fun setUp() {
        interestRateAdapter.defaultInterest = defaultInterest
    }

    @Test
    fun `should return valid interest rate from bacenIntegration when greater than zero`() {
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "6.5"))
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenReturn(response)
        whenever(response.body()).thenReturn(selicRateResponse)
        
        val result = interestRateAdapter.getPercentageInterestRate()
        
        assertEquals(BigDecimal("6.5"), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns null`() {
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenReturn(response)
        whenever(response.body()).thenReturn(null)
        
        val result = interestRateAdapter.getPercentageInterestRate()
        
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns empty list`() {
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenReturn(response)
        whenever(response.body()).thenReturn(emptyList())
        
        val result = interestRateAdapter.getPercentageInterestRate()
        
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns negative interest rate`() {
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "-1.0"))
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenReturn(response)
        whenever(response.body()).thenReturn(selicRateResponse)
        
        val result = interestRateAdapter.getPercentageInterestRate()
        
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns zero interest rate`() {
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "0.0"))
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenReturn(response)
        whenever(response.body()).thenReturn(selicRateResponse)
        
        val result = interestRateAdapter.getPercentageInterestRate()
        
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration throws an exception`() {
        whenever(bacenIntegration.getSelicRate()).thenReturn(call)
        whenever(call.execute()).thenThrow(RuntimeException())

        val result = interestRateAdapter.getPercentageInterestRate()

        assertEquals(BigDecimal(defaultInterest), result)
    }
}