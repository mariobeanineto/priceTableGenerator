package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import java.util.Optional

interface SketchRepositoryInterface {
    suspend fun saveSketch(sketch: Sketch)

    fun getSketch(id: String): Optional<Sketch>
}