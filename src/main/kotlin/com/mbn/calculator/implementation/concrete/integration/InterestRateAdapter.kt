package com.mbn.calculator.implementation.concrete.integration

import com.mbn.calculator.interfaces.InterestRateInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Profile("!mock")
@Service
class InterestRateAdapter(val bacenIntegration: BacenIntegration) : InterestRateInterface {
    override fun getPercentageInterestRate(): BigDecimal {
        return BigDecimal(bacenIntegration.getSelicRate().execute().body()?.first()?.value ?: "ZERO")
    }
}