package com.mbn.calculator.implementation.mock.metrics

import com.mbn.calculator.interfaces.MetricsInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("mock")
@Service
class MockMicrometerAdapter : MetricsInterface {
    override fun addSimulation() {
        print("mocked metrics")
    }
}