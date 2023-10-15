package com.mbn.calculator.metrics

import com.mbn.calculator.interfaces.MetricsInterface
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import org.springframework.stereotype.Service

@Service
class MicrometerAdapter(
        private val meterRegistry: CompositeMeterRegistry
) : MetricsInterface {
    override fun addSimulation() {
        val simulationCounter = meterRegistry.counter("simulation.times")
        simulationCounter.increment()
    }
}