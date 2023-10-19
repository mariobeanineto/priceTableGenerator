package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import java.math.BigDecimal

interface SketchServiceInterface {
    fun createSketch(presentValueAmount: BigDecimal, installmentList: List<Int>, documentNumber: String, name: String): Sketch

    fun getSketch(id: String): Sketch
}