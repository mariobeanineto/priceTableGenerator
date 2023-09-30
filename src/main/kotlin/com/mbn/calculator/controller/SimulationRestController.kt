package com.mbn.calculator.controller

import com.mbn.calculator.business.service.SimulationService
import com.mbn.calculator.controller.domain.SimulationRequest
import com.mbn.calculator.controller.domain.SimulationResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/price-table/v1")
class SimulationRestController(
        val simulationService: SimulationService
): ControllerInterface {

    @PostMapping()
    override fun createSimulation(simulationRequest: SimulationRequest): SimulationResponse {
        TODO("Not yet implemented")
    }

    @GetMapping("/simulation/{id}")
    override fun getSimulation(id: String): SimulationResponse {
        TODO("Not yet implemented")
    }
}