package com.mbn.calculator.controller

import com.mbn.calculator.business.service.SimulationService
import com.mbn.calculator.controller.domain.SimulationRequest
import com.mbn.calculator.controller.domain.SimulationResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/price-table/v1")
class SimulationRestController(
        val simulationService: SimulationService
) : ControllerInterface {

    @PostMapping("/simulation")
    override fun createSimulation(@RequestBody simulationRequest: SimulationRequest): ResponseEntity<SimulationResponse> {
        val simulation = simulationService.createSimulation(simulationRequest.amount,
                simulationRequest.installmentNumber,
                simulationRequest.documentNumber,
                simulationRequest.name
        )
        val response = SimulationResponse.from(simulation)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/simulation/{id}")
    override fun getSimulation(id: String): ResponseEntity<SimulationResponse> {
        TODO("Not yet implemented")
    }
}