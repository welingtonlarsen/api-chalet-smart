package br.com.chaletSmart.domain.register.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class AlarmTriggerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var date: LocalDateTime
) {

    constructor() : this(null, LocalDateTime.now())
}
