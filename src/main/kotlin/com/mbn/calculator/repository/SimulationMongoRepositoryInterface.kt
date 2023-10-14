package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.SimulationMongo
import org.springframework.data.repository.CrudRepository

interface SimulationMongoRepositoryInterface : CrudRepository<SimulationMongo, Long> {
}