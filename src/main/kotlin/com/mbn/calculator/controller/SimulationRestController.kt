package com.mbn.calculator.controller

import com.mbn.calculator.business.domain.PriceTable
import com.mbn.calculator.business.service.InstallmentService
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
    override fun createSimulation(@RequestBody simulationRequest: SimulationRequest): ResponseEntity<List<PriceTable>> {
        return ResponseEntity(simulationService.createSimulation(simulationRequest.amount, simulationRequest.installmentNumber), HttpStatus.CREATED)
    }

    @GetMapping("/simulation/{id}")
    override fun getSimulation(id: String): ResponseEntity<PriceTable> {
        TODO("Not yet implemented")
    }
}