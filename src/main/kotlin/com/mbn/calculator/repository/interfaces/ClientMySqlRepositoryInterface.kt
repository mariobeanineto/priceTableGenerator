package com.mbn.calculator.repository.interfaces

import com.mbn.calculator.repository.domain.mysql.ClientMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientMySqlRepositoryInterface: JpaRepository<ClientMySql, Long>