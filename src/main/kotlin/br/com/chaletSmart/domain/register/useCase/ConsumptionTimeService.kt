package br.com.chaletSmart.domain.register.useCase

import br.com.chaletSmart.domain.register.enums.ConsumptionType
import br.com.chaletSmart.domain.register.model.ConsumptionTimeEntity
import java.time.LocalDate

interface ConsumptionTimeService {

    fun turnOnTheLight(consumptionType: ConsumptionType)

    fun turnOffTheLight(consumptionType: ConsumptionType)

    fun getTotalSecondsOfAllConsumptions(): Long

    fun findAllConsumptionTime(): List<ConsumptionTimeEntity>

    fun findAllConsumptionTimeGroupedByDate(): Map<LocalDate, Long>
}
