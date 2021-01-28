package br.com.chaletSmart.domain.register.useCase

import br.com.chaletSmart.domain.register.enums.SwitcherType
import br.com.chaletSmart.domain.register.model.SwitcherTriggeredEntity
import java.time.LocalDate

interface SwitcherTriggeredService {

    fun turnOnTheLight(switcherType: SwitcherType)

    fun turnOffTheLight(switcherType: SwitcherType)

    fun getTotalSecondsOfAllSwitchersTriggered(): Long

    fun findAllSwitchersTriggered(): List<SwitcherTriggeredEntity>

    fun findAllSwitchersTriggeredGroupedByDate(): Map<LocalDate, Long>
}
