package com.api.smart.chalet.persistence.repositories

import com.api.smart.chalet.persistence.entities.ConsumptionTimeEntity
import com.api.smart.chalet.persistence.enums.ConsumptionType
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.time.LocalDate

@Repository
interface ConsumptionTimeRepository : CrudRepository<ConsumptionTimeEntity, Long> {

    fun findByConsumptionTypeOrderByIdDesc(consumptionType: ConsumptionType): ConsumptionTimeEntity

    fun findSumTotalTimeByConsumptionDateEquals(consumptionDate: LocalDate): Long

}