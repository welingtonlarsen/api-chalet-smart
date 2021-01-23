package br.com.chaletSmart.domain.register.repository

import br.com.chaletSmart.domain.register.enums.ConsumptionType
import br.com.chaletSmart.domain.register.model.ConsumptionTimeEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.time.LocalDate

@Repository
interface ConsumptionTimeRepository : CrudRepository<ConsumptionTimeEntity, Long> {

    fun findByConsumptionTypeOrderByIdDesc(consumptionType: ConsumptionType): ConsumptionTimeEntity

    fun findSumTotalTimeByConsumptionDateEquals(consumptionDate: LocalDate): Long
}
