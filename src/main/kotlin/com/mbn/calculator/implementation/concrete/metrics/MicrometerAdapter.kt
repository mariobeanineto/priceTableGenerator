package com.mbn.calculator.implementation.concrete.metrics

import com.mbn.calculator.interfaces.MetricsInterface
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("!mock")
@Service
class MicrometerAdapter(
        private val meterRegistry: CompositeMeterRegistry
) : MetricsInterface {
    override fun addSimulation() {
        meterRegistry.counter("simulation.times")
    }
}