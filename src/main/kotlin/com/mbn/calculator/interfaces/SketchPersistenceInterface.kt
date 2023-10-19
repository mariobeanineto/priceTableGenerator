package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Sketch

interface SketchPersistenceInterface {
    suspend fun saveSketch(sketch: Sketch)

    fun getSketch(id: String): Sketch
}