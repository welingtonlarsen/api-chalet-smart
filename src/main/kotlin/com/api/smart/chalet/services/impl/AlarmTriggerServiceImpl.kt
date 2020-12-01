package com.api.smart.chalet.services.impl

import com.api.smart.chalet.persistence.entities.AlarmTriggerEntity
import com.api.smart.chalet.persistence.repositories.AlarmTriggerRepository
import com.api.smart.chalet.services.interfaces.AlarmTriggerService
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