package br.com.chaletSmart.domain.register.repository

import br.com.chaletSmart.domain.register.model.AlarmTriggerEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AlarmTriggerRepository : CrudRepository<AlarmTriggerEntity, Long>
