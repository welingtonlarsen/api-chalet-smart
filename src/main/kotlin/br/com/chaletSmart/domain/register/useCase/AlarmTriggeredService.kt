package br.com.chaletSmart.domain.register.useCase

import br.com.chaletSmart.domain.register.model.AlarmTriggeredEntity

interface AlarmTriggeredService {

    fun triggerAlarm()

    fun findAllAlarmTriggered(): List<AlarmTriggeredEntity>
}
