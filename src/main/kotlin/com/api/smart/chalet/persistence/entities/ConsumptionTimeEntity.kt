package com.api.smart.chalet.persistence.entities

import com.api.smart.chalet.persistence.enums.ConsumptionType
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*

@Entity
data class ConsumptionTimeEntity(@Id
                                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                                 var id: Long?,
                                 var initialDateTime: LocalDateTime,
                                 var finalDateTime: LocalDateTime?,
                                 var consumptionDate: LocalDate,
                                 var consumptionType: ConsumptionType?,
                                 var totalTime: Long?) {

    constructor() : this(null, LocalDateTime.now(), null, LocalDate.now(), null, null)

    constructor(consumptionTypeCommand: ConsumptionTimeCommand) : this (null, LocalDateTime.now(), null, LocalDate.now(), consumptionTypeCommand.consumptionType, null)

    fun calculateTotalTime() {
        totalTime = Duration.between(initialDateTime, finalDateTime).toSeconds()
    }

}

data class ConsumptionTimeCommand(
        var consumptionType: ConsumptionType
)