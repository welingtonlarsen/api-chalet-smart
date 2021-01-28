package br.com.chaletSmart.api.rest.useCase

import br.com.chaletSmart.domain.register.enums.SwitcherType
import br.com.chaletSmart.domain.register.model.SaveSwitcherTriggeredCommand
import br.com.chaletSmart.domain.register.model.SwitcherTriggeredEntity
import br.com.chaletSmart.domain.register.repository.ConsumptionTimeRepository
import br.com.chaletSmart.domain.register.useCase.SwitcherTriggeredService
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwitcherTriggeredServiceImpl(
    @Inject
    private val consumptionTimeRepository: ConsumptionTimeRepository
) : SwitcherTriggeredService {

    override fun turnOnTheLight(switcherType: SwitcherType) {
        var consumptionTimeEntity = SwitcherTriggeredEntity(SaveSwitcherTriggeredCommand(switcherType))
        consumptionTimeRepository.save(consumptionTimeEntity)
    }

    override fun turnOffTheLight(switcherType: SwitcherType) {
        var consumptionTimeEntity = consumptionTimeRepository.findBySwitcherTypeOrderByIdDesc(switcherType)
        if (consumptionTimeEntity.finalDateTime == null) {
            consumptionTimeEntity.finalDateTime = LocalDateTime.now()
            consumptionTimeEntity.calculateTotalTime()
            consumptionTimeRepository.update(consumptionTimeEntity)
        }
    }

    override fun getTotalSecondsOfAllSwitchersTriggered(): Long {
        var totalSeconds = 0L
        consumptionTimeRepository.findAll()
            .filter { it.finalDateTime != null && it.initialDateTime != null }
            .forEach { totalSeconds += Duration.between(it.initialDateTime, it.finalDateTime!!).toSeconds() }
        return totalSeconds
    }

    override fun findAllSwitchersTriggered(): List<SwitcherTriggeredEntity> {
        return consumptionTimeRepository.findAll().toList().filter {
            it.finalDateTime != null && it.switcherType != null && it.totalTime != null
        }
    }

    override fun findAllSwitchersTriggeredGroupedByDate(): Map<LocalDate, Long> {
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
