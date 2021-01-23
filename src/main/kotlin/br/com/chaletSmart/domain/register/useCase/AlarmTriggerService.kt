package br.com.chaletSmart.domain.register.useCase

import br.com.chaletSmart.domain.register.model.AlarmTriggerEntity

interface AlarmTriggerService {

    fun triggerAlarm()

    fun findAllAlarmTriggred(): List<AlarmTriggerEntity>
}
