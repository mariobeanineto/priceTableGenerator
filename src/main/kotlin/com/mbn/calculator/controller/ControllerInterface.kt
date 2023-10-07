package com.mbn.calculator.controller

import com.mbn.calculator.controller.domain.SimulationRequest
import com.mbn.calculator.controller.domain.SimulationResponse
import org.springframework.http.ResponseEntity

interface ControllerInterface {
    fun createSimulation(simulationRequest: SimulationRequest): ResponseEntity<SimulationResponse>

    fun getSimulation(id: String): ResponseEntity<SimulationResponse>
}