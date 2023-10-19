package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.SketchPersistenceInterface
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.Optional

@Profile("!mock")
@Repository
class SketchPersistenceAdapter(
        val repositories: List<SketchRepositoryInterface>
) : SketchPersistenceInterface {
    override suspend fun saveSketch(sketch: Sketch) {
        repositories.forEach {
            it.saveSketch(sketch)
        }
    }

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