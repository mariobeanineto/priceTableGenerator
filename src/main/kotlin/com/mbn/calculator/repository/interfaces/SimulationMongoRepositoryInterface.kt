package com.mbn.calculator.repository.interfaces

import com.mbn.calculator.repository.domain.mongo.SimulationMongo
import org.springframework.data.repository.CrudRepository

interface SimulationMongoRepositoryInterface : CrudRepository<SimulationMongo, Long> {
}