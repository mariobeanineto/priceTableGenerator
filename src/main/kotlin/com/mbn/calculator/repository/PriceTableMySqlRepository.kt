package com.mbn.calculator.repository

import com.mbn.calculator.domain.repository.InstallmentMySql
import com.mbn.calculator.domain.repository.PriceTableMySql
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PriceTableMySqlRepository: JpaRepository<PriceTableMySql, Long>