package br.com.chaletSmart.api.rest.useCase

import br.com.chaletSmart.domain.register.enums.ConsumptionType
import br.com.chaletSmart.domain.register.model.ConsumptionTimeCommand
import br.com.chaletSmart.domain.register.model.ConsumptionTimeEntity
import br.com.chaletSmart.domain.register.repository.ConsumptionTimeRepository
import br.com.chaletSmart.domain.register.useCase.ConsumptionTimeService
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConsumptionTimeServiceImpl(
    @Inject
    private val consumptionTimeRepository: ConsumptionTimeRepository
) : ConsumptionTimeService {

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
