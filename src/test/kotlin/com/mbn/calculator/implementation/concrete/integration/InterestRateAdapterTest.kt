package com.mbn.calculator.implementation.concrete.integration

import com.mbn.calculator.implementation.concrete.domain.integration.SelicResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
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
        // Arrange
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "6.5"))
        `when`(bacenIntegration.getSelicRate()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)
        `when`(response.body()).thenReturn(selicRateResponse)

        // Act
        val result = interestRateAdapter.getPercentageInterestRate()

        // Assert
        assertEquals(BigDecimal("6.5"), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns null`() {
        // Arrange
        `when`(bacenIntegration.getSelicRate()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)
        `when`(response.body()).thenReturn(null)

        // Act
        val result = interestRateAdapter.getPercentageInterestRate()

        // Assert
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns empty list`() {
        // Arrange
        `when`(bacenIntegration.getSelicRate()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)
        `when`(response.body()).thenReturn(emptyList())

        // Act
        val result = interestRateAdapter.getPercentageInterestRate()

        // Assert
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns negative interest rate`() {
        // Arrange
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "-1.0"))
        `when`(bacenIntegration.getSelicRate()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)
        `when`(response.body()).thenReturn(selicRateResponse)

        // Act
        val result = interestRateAdapter.getPercentageInterestRate()

        // Assert
        assertEquals(BigDecimal(defaultInterest), result)
    }

    @Test
    fun `should return default interest rate when bacenIntegration returns zero interest rate`() {
        // Arrange
        val selicRateResponse = listOf(SelicResponse("2024-10-20", "0.0"))
        `when`(bacenIntegration.getSelicRate()).thenReturn(call)
        `when`(call.execute()).thenReturn(response)
        `when`(response.body()).thenReturn(selicRateResponse)

        // Act
        val result = interestRateAdapter.getPercentageInterestRate()

        // Assert
        assertEquals(BigDecimal(defaultInterest), result)
    }
}