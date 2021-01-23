package br.com.chaletSmart.api.rest.useCase

import br.com.chaletSmart.domain.register.model.AlarmTriggerEntity
import br.com.chaletSmart.domain.register.repository.AlarmTriggerRepository
import br.com.chaletSmart.domain.register.useCase.AlarmTriggerService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmsTriggerServiceImpl(@Inject val alarmTriggerRepository: AlarmTriggerRepository) : AlarmTriggerService {

    override fun triggerAlarm() {
        alarmTriggerRepository.save(AlarmTriggerEntity())
    }

    override fun findAllAlarmTriggred(): List<AlarmTriggerEntity> {
        return alarmTriggerRepository.findAll().toList()
    }
}
