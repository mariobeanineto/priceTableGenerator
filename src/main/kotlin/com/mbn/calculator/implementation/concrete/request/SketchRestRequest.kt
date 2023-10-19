package com.mbn.calculator.implementation.concrete.request

import com.mbn.calculator.implementation.concrete.domain.request.SketchRequest
import com.mbn.calculator.implementation.concrete.domain.request.SketchResponse
import com.mbn.calculator.interfaces.SketchServiceInterface
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/price-table/v1")
class SketchRestRequest(
        val sketchServiceInterface: SketchServiceInterface
) {

    @PostMapping("/sketch")
    fun createSketch(@RequestBody sketchRequest: SketchRequest): ResponseEntity<SketchResponse> {
        val sketch = sketchServiceInterface.createSketch(sketchRequest.amount,
                sketchRequest.installmentNumber,
                sketchRequest.documentNumber,
                sketchRequest.name
        )
        val response = SketchResponse.from(sketch)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/sketch/{id}")
    fun getSketch(@PathVariable id: String): ResponseEntity<SketchResponse> {
        val response = SketchResponse.from(sketchServiceInterface.getSketch(id))
        return ResponseEntity(response, HttpStatus.OK)
    }
}