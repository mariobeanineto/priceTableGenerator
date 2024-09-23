package com.mbn.calculator.implementation.concrete.request

import com.mbn.calculator.implementation.concrete.exceptions.CreateSketchException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdviceRestRequest : ResponseEntityExceptionHandler() {

    @ExceptionHandler(SketchNotFoundException::class)
    fun sketchNotFoundException(sketchNotFoundException: SketchNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(sketchNotFoundException.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(CreateSketchException::class)
    fun createSketchException(sketchNotFoundException: SketchNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(sketchNotFoundException.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun createSketchException(illegalArgumentException: IllegalArgumentException): ResponseEntity<Any> {
        return ResponseEntity(illegalArgumentException.message, HttpStatus.BAD_REQUEST)
    }
}