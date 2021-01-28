package br.com.chaletSmart.api.rest.endpoint

import br.com.chaletSmart.domain.register.enums.SwitcherType
import br.com.chaletSmart.domain.register.model.SwitcherTriggeredEntity
import br.com.chaletSmart.domain.register.useCase.SwitcherTriggeredService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import java.time.LocalDate
import javax.inject.Inject

@Controller("/consumptionTime")
class ConsumptionTimeController(@Inject private val switcherTriggeredService: SwitcherTriggeredService) {

    @Post("/turnOn/{consumptionType}")
    fun turnOnTheLight(consumptionType: String): HttpStatus {
        switcherTriggeredService.turnOnTheLight(SwitcherType.valueOf(consumptionType))
        return HttpStatus.ACCEPTED
    }

    @Post("/turnOff/{consumptionType}")
    fun turnOfTheLight(consumptionType: String): HttpStatus {
        switcherTriggeredService.turnOffTheLight(SwitcherType.valueOf(consumptionType))
        return HttpStatus.ACCEPTED
    }

    @Get("/totalSeconds")
    fun getTotalSeconds(): HttpResponse<Long> {
        var totalSeconds = switcherTriggeredService.getTotalSecondsOfAllSwitchersTriggered()
        return HttpResponse.ok(totalSeconds)
    }

    @Get
    fun findAllConsumptionTime(): HttpResponse<List<SwitcherTriggeredEntity>> {
        var consumptionsTime = switcherTriggeredService.findAllSwitchersTriggered()
        return HttpResponse.ok(consumptionsTime)
    }

    @Get("/groupedByDate")
    fun findAllGroupedByDate(): HttpResponse<Map<LocalDate, Long>> {
        return HttpResponse.ok(switcherTriggeredService.findAllSwitchersTriggeredGroupedByDate())
    }
}
