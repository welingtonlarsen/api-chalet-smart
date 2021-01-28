package br.com.chaletSmart.api.rest.useCase

import br.com.chaletSmart.domain.register.model.AlarmTriggeredEntity
import br.com.chaletSmart.domain.register.repository.AlarmTriggerRepository
import br.com.chaletSmart.domain.register.useCase.AlarmTriggeredService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmsTriggeredServiceImpl(
    @Inject
    val alarmTriggerRepository: AlarmTriggerRepository
) : AlarmTriggeredService {

    override fun triggerAlarm() {
        alarmTriggerRepository.save(AlarmTriggeredEntity())
    }

    override fun findAllAlarmTriggered(): List<AlarmTriggeredEntity> {
        return alarmTriggerRepository.findAll().toList()
    }
}
