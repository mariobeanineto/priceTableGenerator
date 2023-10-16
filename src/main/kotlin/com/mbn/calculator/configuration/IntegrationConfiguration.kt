package com.mbn.calculator.configuration

import com.mbn.calculator.implementation.concrete.integration.BacenIntegration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Profile("!mock")
@Configuration
class IntegrationConfiguration {

    @get:Bean
    val bacenIntegration = getClient().create(BacenIntegration::class.java)

    private final fun getClient(): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://api.bcb.gov.br")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build()
}