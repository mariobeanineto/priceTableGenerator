package com.mbn.calculator.repository.interfaces

import com.mbn.calculator.repository.domain.mysql.InstallmentMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InstallmentMySqlRepositoryInterface: JpaRepository<InstallmentMySql, Long>