package com.api.smart.chalet.persistence.repositories

import com.api.smart.chalet.persistence.entities.AlarmTriggerEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface AlarmTriggerRepository : CrudRepository<AlarmTriggerEntity, Long> {
}