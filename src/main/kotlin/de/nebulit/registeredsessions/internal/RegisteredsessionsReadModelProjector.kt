package de.nebulit.registeredsessions.internal

import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID
import de.nebulit.events.SessionRegisteredEvent
import de.nebulit.registeredsessions.RegisteredsessionsReadModelEntity


import mu.KotlinLogging

interface RegisteredsessionsReadModelRepository : JpaRepository<RegisteredsessionsReadModelEntity, UUID>

@Component
class RegisteredsessionsReadModelProjector(
    var repository: RegisteredsessionsReadModelRepository
) {


    @EventHandler
    fun on(event: SessionRegisteredEvent) {
        //throws exception if not available (adjust logic)
        val entity = this.repository.findById(event.aggregateId).orElse(RegisteredsessionsReadModelEntity())
        entity.apply {
            aggregateId = event.aggregateId
            abstract = event.abstract
            presenter = event.presenter
            topic = event.topic
        }.also { this.repository.save(it) }
    }

}
