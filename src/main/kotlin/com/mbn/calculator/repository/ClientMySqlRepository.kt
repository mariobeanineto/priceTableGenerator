package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.ClientMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientMySqlRepositoryInterface: JpaRepository<ClientMySql, Long>