package com.mbn.calculator.implementation.concrete.persistence.mysql

import com.mbn.calculator.implementation.concrete.domain.persistance.ClientMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientMySqlRepositoryInterface : JpaRepository<ClientMySql, Long>