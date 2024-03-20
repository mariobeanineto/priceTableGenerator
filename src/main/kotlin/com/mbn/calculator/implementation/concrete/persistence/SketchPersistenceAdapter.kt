package com.mbn.calculator.implementation.concrete.persistence

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.PersistenceException
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.SketchPersistenceInterface
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import kotlin.jvm.Throws

@Profile("!mock")
@Repository
class SketchPersistenceAdapter(
    val repositories: List<SketchRepositoryInterface>
) : SketchPersistenceInterface {

    @Throws(PersistenceException::class)
    override suspend fun saveSketch(sketch: Sketch) {
        try {
            repositories.forEach {
                it.saveSketch(sketch)
            }
        } catch (e: PersistenceException) {
            throw e
        }
    }

    @Throws(SketchNotFoundException::class)
    override fun getSketch(id: String): Sketch {
        repositories.forEach {
            val sketch = it.getSketch(id)
            if (sketch.isPresent) {
                return sketch.get()
            }
        }
        throw SketchNotFoundException("sketch id $id not found")
    }
}