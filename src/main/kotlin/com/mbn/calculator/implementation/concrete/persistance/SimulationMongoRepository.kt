package com.mbn.calculator.implementation.concrete.persistance

import com.mbn.calculator.implementation.concrete.domain.persistance.SimulationMongo
import org.springframework.data.repository.CrudRepository

interface SimulationMongoRepository : CrudRepository<SimulationMongo, Long> {
}