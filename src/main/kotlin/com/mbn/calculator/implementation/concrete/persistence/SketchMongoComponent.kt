package com.mbn.calculator.implementation.concrete.persistence

import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMongo
import com.mbn.calculator.implementation.concrete.domain.business.Sketch
import com.mbn.calculator.implementation.concrete.exceptions.MongoException
import com.mbn.calculator.implementation.concrete.exceptions.PersistenceException
import com.mbn.calculator.interfaces.SketchRepositoryInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.Throws

@Profile("mongo")
@Repository
class SketchMongoComponent(
    val sketchMongoRepository: SketchMongoRepository
) : SketchRepositoryInterface {

    @Throws(MongoException::class)
    override suspend fun saveSketch(sketch: Sketch) {
        try {
            sketchMongoRepository.save(SketchMongo.from(sketch))
        } catch (e: Exception) {
            throw MongoException(e.message!!)
        }
    }

    override fun getSketch(id: String): Optional<Sketch> {
        return try {
            Optional.of(Sketch.from(sketchMongoRepository.findById(id).get()))
        } catch (e: NoSuchElementException) {
            Optional.empty<Sketch>()
        }
    }
}