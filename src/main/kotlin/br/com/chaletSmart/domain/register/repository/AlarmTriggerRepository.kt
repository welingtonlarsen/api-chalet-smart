package br.com.chaletSmart.domain.register.repository

import br.com.chaletSmart.domain.register.model.AlarmTriggeredEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AlarmTriggerRepository : CrudRepository<AlarmTriggeredEntity, Long>
