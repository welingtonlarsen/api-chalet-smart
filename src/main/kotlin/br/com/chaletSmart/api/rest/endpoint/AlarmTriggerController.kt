package br.com.chaletSmart.api.rest.endpoint

import br.com.chaletSmart.domain.register.model.AlarmTriggerEntity
import br.com.chaletSmart.domain.register.useCase.AlarmTriggerService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/alarm")
class AlarmTriggerController(@Inject private val alarmTriggerService: AlarmTriggerService) {

    @Get
    fun findAllAlarmsTriggered(): HttpResponse<List<AlarmTriggerEntity>> {
        val allAlarmsTriggered = alarmTriggerService.findAllAlarmTriggred()
        return HttpResponse.ok(allAlarmsTriggered)
    }

    @Post
    fun triggerAlarm(): HttpStatus {
        alarmTriggerService.triggerAlarm()
        return HttpStatus.ACCEPTED
    }
}
