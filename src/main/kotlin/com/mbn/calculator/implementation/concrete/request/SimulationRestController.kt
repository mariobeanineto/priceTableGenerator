package com.mbn.calculator.implementation.concrete.request

import com.mbn.calculator.implementation.concrete.domain.request.SimulationRequest
import com.mbn.calculator.implementation.concrete.domain.request.SimulationResponse
import com.mbn.calculator.interfaces.SimulationServiceInterface
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/price-table/v1")
class SimulationRestController(
        val simulationServiceInterface: SimulationServiceInterface
) {

    @PostMapping("/simulation")
    fun createSimulation(@RequestBody simulationRequest: SimulationRequest): ResponseEntity<SimulationResponse> {
        val simulation = simulationServiceInterface.createSimulation(simulationRequest.amount,
                simulationRequest.installmentNumber,
                simulationRequest.documentNumber,
                simulationRequest.name
        )
        val response = SimulationResponse.from(simulation)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/simulation/{id}")
    fun getSimulation(id: String): ResponseEntity<SimulationResponse> {
        TODO("Not yet implemented")
    }
}