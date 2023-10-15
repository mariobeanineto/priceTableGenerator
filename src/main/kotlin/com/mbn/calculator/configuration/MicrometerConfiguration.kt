package com.mbn.calculator.configuration

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MicrometerConfiguration {

    @Bean
    fun getMeterRegistry(): MeterRegistry {
        return CompositeMeterRegistry()
    }
}