package com.mbn.calculator.business.service

import com.mbn.calculator.integration.BacenIntegration
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class RateService(
        val bacenIntegration: BacenIntegration
) {
    fun getSelicRate(): BigDecimal {
        return BigDecimal(bacenIntegration.getSelicRate().execute().body()?.first()?.valor ?: "ZERO")
    }
}