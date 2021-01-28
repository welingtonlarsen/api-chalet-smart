package br.com.chaletSmart.api.rest.endpoint

import br.com.chaletSmart.domain.register.model.AlarmTriggeredEntity
import br.com.chaletSmart.domain.register.useCase.AlarmTriggeredService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/alarm")
class AlarmTriggerController(@Inject private val alarmTriggeredService: AlarmTriggeredService) {

    @Get
    fun findAllAlarmsTriggered(): HttpResponse<List<AlarmTriggeredEntity>> {
        val allAlarmsTriggered = alarmTriggeredService.findAllAlarmTriggered()
        return HttpResponse.ok(allAlarmsTriggered)
    }

    @Post
    fun triggerAlarm(): HttpStatus {
        alarmTriggeredService.triggerAlarm()
        return HttpStatus.ACCEPTED
    }
}
