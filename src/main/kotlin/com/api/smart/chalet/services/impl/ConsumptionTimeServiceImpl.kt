package com.api.smart.chalet.services.impl

import com.api.smart.chalet.dtos.ConsumptionTimeDto
import com.api.smart.chalet.persistence.entities.ConsumptionTimeCommand
import com.api.smart.chalet.persistence.entities.ConsumptionTimeEntity
import com.api.smart.chalet.persistence.enums.ConsumptionType
import com.api.smart.chalet.persistence.repositories.ConsumptionTimeRepository
import com.api.smart.chalet.services.interfaces.ConsumptionTimeService
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsumptionTimeServiceImpl(@Inject private val consumptionTimeRepository: ConsumptionTimeRepository) : ConsumptionTimeService {

    override fun turnOnTheLight(consumptionType: ConsumptionType) {
        var consumptionTimeEntity = ConsumptionTimeEntity(ConsumptionTimeCommand(consumptionType))
        consumptionTimeRepository.save(consumptionTimeEntity)
    }

    override fun turnOffTheLight(consumptionType: ConsumptionType) {
        var consumptionTimeEntity = consumptionTimeRepository.findByConsumptionTypeOrderByIdDesc(consumptionType)
        if (consumptionTimeEntity.finalDateTime == null) {
            consumptionTimeEntity.finalDateTime = LocalDateTime.now()
            consumptionTimeEntity.calculateTotalTime()
            consumptionTimeRepository.update(consumptionTimeEntity)
        }
    }

    override fun getTotalSecondsOfAllConsumptions(): Long {
        var totalSeconds = 0L
        consumptionTimeRepository.findAll()
                .filter { it.finalDateTime != null && it.initialDateTime != null }
                .forEach { totalSeconds += Duration.between(it.initialDateTime, it.finalDateTime!!).toSeconds() }
        return totalSeconds
    }

    override fun findAllConsumptionTime(): List<ConsumptionTimeEntity> {
        return consumptionTimeRepository.findAll().toList().filter {
            it.finalDateTime != null && it.consumptionType != null && it.totalTime != null
        }
    }

    override fun findAllConsumptionTimeGroupedByDate(): Map<LocalDate, Long> {
        val allConsumptionsTime = consumptionTimeRepository.findAll().toList()
        var consumptionsByDate: MutableMap<LocalDate, Long> = mutableMapOf()

        if (allConsumptionsTime.isNotEmpty()) {

            allConsumptionsTime.forEach {
                if (it.consumptionDate != null && it.finalDateTime != null) {
                    if (consumptionsByDate[it.consumptionDate] != null) {
                        consumptionsByDate[it.consumptionDate] = consumptionsByDate[it.consumptionDate]?.plus(10L)!!
                    } else {
                        consumptionsByDate[it.consumptionDate] = it.totalTime!!
                    }
                }
            }

        }

        return consumptionsByDate!!
    }


}