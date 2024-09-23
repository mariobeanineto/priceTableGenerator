package com.mbn.calculator.interfaces

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.PersistenceException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import kotlin.jvm.Throws

interface SketchPersistenceInterface {
    @Throws(PersistenceException::class)
    suspend fun saveSketch(sketch: Sketch)

    @Throws(SketchNotFoundException::class)
    fun getSketch(id: String): Sketch
}