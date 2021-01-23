package br.com.chaletSmart.api.rest.endpoint

import br.com.chaletSmart.domain.register.enums.ConsumptionType
import br.com.chaletSmart.domain.register.model.ConsumptionTimeEntity
import br.com.chaletSmart.domain.register.useCase.ConsumptionTimeService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import java.time.LocalDate
import javax.inject.Inject

@Controller("/consumptionTime")
class ConsumptionTimeController(@Inject private val consumptionTimeService: ConsumptionTimeService) {

    @Post("/turnOn/{consumptionType}")
    fun turnOnTheLight(consumptionType: String): HttpStatus {
        consumptionTimeService.turnOnTheLight(ConsumptionType.valueOf(consumptionType))
        return HttpStatus.ACCEPTED
    }

    @Post("/turnOff/{consumptionType}")
    fun turnOfTheLight(consumptionType: String): HttpStatus {
        consumptionTimeService.turnOffTheLight(ConsumptionType.valueOf(consumptionType))
        return HttpStatus.ACCEPTED
    }

    @Get("/totalSeconds")
    fun getTotalSeconds(): HttpResponse<Long> {
        var totalSeconds = consumptionTimeService.getTotalSecondsOfAllConsumptions()
        return HttpResponse.ok(totalSeconds)
    }

    @Get
    fun findAllConsumptionTime(): HttpResponse<List<ConsumptionTimeEntity>> {
        var consumptionsTime = consumptionTimeService.findAllConsumptionTime()
        return HttpResponse.ok(consumptionsTime)
    }

    @Get("/groupedByDate")
    fun findAllGroupedByDate(): HttpResponse<Map<LocalDate, Long>> {
        return HttpResponse.ok(consumptionTimeService.findAllConsumptionTimeGroupedByDate())
    }
}
