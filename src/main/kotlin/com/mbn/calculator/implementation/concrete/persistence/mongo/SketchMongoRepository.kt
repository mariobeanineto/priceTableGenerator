package com.mbn.calculator.implementation.concrete.persistence.mongo

import com.mbn.calculator.implementation.concrete.domain.persistance.SketchMongo
import org.springframework.data.repository.CrudRepository

interface SketchMongoRepository : CrudRepository<SketchMongo, String> {
}