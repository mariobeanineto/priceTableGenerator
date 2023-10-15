package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.InstallmentMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstallmentMySqlRepository: JpaRepository<InstallmentMySql, Long>