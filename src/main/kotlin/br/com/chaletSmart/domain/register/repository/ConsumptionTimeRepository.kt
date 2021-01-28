package br.com.chaletSmart.domain.register.repository

import br.com.chaletSmart.domain.register.enums.SwitcherType
import br.com.chaletSmart.domain.register.model.SwitcherTriggeredEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.time.LocalDate

@Repository
interface ConsumptionTimeRepository : CrudRepository<SwitcherTriggeredEntity, Long> {

    fun findBySwitcherTypeOrderByIdDesc(switcherType: SwitcherType): SwitcherTriggeredEntity

    fun findSumTotalTimeByConsumptionDateEquals(consumptionDate: LocalDate): Long
}
