package com.mbn.calculator.configuration

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Profile("!mock")
@Configuration
class MicrometerConfiguration {

    @Bean
    fun getMeterRegistry(): MeterRegistry {
        return CompositeMeterRegistry()
    }
}