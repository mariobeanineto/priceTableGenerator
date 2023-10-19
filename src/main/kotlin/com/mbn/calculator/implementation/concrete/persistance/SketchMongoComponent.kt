package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMongo
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.SketchNotFoundException
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.*

@Profile("mongo")
@Repository
class SketchMongoComponent(
        val sketchMongoRepository: SketchMongoRepository
) : SketchRepositoryInterface {
    override suspend fun saveSketch(sketch: Sketch) {
        sketchMongoRepository.save(SketchMongo.from(sketch))
    }

    override fun getSketch(id: String): Optional<Sketch> {
        return try {
            Optional.of(Sketch.from(sketchMongoRepository.findById(id).get()))
        } catch(e: NoSuchElementException) {
            Optional.empty<Sketch>()
        }
    }
}