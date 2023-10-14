package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.SimulationMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SimulationMySqlRepositoryInterface: JpaRepository<SimulationMySql, Long>