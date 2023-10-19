package com.mbn.calculator.implementation.concrete.request

import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdviceRestRequest: ResponseEntityExceptionHandler() {

    @ExceptionHandler(SketchNotFoundException::class)
    fun sketchNotFoundException(sketchNotFoundException: SketchNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(sketchNotFoundException.message, HttpStatus.NOT_FOUND)
    }
}