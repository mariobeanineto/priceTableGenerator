package com.mbn.calculator.implementation.concrete.integration

import com.mbn.calculator.interfaces.InterestRateInterface
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.math.BigDecimal
import kotlin.math.max

@Profile("!mock")
@Service
class InterestRateAdapter(val bacenIntegration: BacenIntegration) : InterestRateInterface {
    @Value("\${sketch.default.interest.rate}")
    lateinit var defaultInterest: String

    override fun getPercentageInterestRate(): BigDecimal {
        return try {
            bacenIntegration.getSelicRate().execute().body()
                ?.first()?.value
                ?.let { BigDecimal(it) }
                ?.takeIf { it > BigDecimal.ZERO }
                ?: BigDecimal(defaultInterest)
        } catch (e: RuntimeException) {
            BigDecimal(defaultInterest)
        }
    }
}