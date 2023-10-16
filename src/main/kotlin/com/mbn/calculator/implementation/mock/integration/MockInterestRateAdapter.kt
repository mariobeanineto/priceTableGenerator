package com.mbn.calculator.implementation.mock.integration

import com.mbn.calculator.interfaces.InterestRateInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Profile("mock")
@Service
class MockInterestRateAdapter() : InterestRateInterface {
    override fun getInterestRate(): BigDecimal {
        return BigDecimal.TEN
    }
}