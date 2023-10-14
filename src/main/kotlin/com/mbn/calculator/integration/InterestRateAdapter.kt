package com.mbn.calculator.integration

import com.mbn.calculator.interfaces.InterestRateInterface
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class InterestRateAdapter(val bacenIntegration: BacenIntegration) : InterestRateInterface {
    override fun getInterestRate(): BigDecimal {
        return BigDecimal(bacenIntegration.getSelicRate().execute().body()?.first()?.value ?: "ZERO")
    }
}