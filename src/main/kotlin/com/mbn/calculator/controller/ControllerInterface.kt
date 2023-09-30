package com.mbn.calculator.controller

import com.mbn.calculator.controller.domain.SimulationRequest
import com.mbn.calculator.controller.domain.SimulationResponse

interface ControllerInterface {
    fun createSimulation(simulationRequest: SimulationRequest): SimulationResponse

    fun getSimulation(id: String): SimulationResponse
}