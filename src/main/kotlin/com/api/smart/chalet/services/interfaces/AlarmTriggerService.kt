package com.api.smart.chalet.services.interfaces

import com.api.smart.chalet.persistence.entities.AlarmTriggerEntity

interface AlarmTriggerService {

    fun triggerAlarm()

    fun findAllAlarmTriggred(): List<AlarmTriggerEntity>
}