package com.mbn.calculator.controller

import com.mbn.calculator.business.domain.PriceTable
import com.mbn.calculator.controller.domain.SimulationRequest
import org.springframework.http.ResponseEntity

interface ControllerInterface {
    fun createSimulation(simulationRequest: SimulationRequest): ResponseEntity<List<PriceTable>>

    fun getSimulation(id: String): ResponseEntity<PriceTable>
}