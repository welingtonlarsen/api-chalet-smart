package com.api.smart.chalet.services.interfaces

import com.api.smart.chalet.dtos.ConsumptionTimeDto
import com.api.smart.chalet.persistence.entities.ConsumptionTimeEntity
import com.api.smart.chalet.persistence.enums.ConsumptionType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ConsumptionTimeService {

    fun turnOnTheLight(consumptionType: ConsumptionType)

    fun turnOffTheLight(consumptionType: ConsumptionType)

    fun getTotalSecondsOfAllConsumptions(): Long

    fun findAllConsumptionTime(): List<ConsumptionTimeEntity>

    fun findAllConsumptionTimeGroupedByDate(): Map<LocalDate, Long>

}