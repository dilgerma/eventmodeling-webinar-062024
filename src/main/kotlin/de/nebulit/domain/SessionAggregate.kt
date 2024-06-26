package de.nebulit.domain

import de.nebulit.domain.commands.registersession.RegisterSessionCommand
import de.nebulit.events.SessionRegisteredEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID


@Aggregate
class SessionAggregate {

    @AggregateIdentifier
    lateinit var aggregateId: UUID


    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    @CommandHandler
    fun handle(command: RegisterSessionCommand) {
        // validierung
        AggregateLifecycle.apply(
            SessionRegisteredEvent(
                command.aggregateId,
                command.abstract, command.presenter, command.topic
            )
        )
    }

    @EventSourcingHandler
    fun on(event: SessionRegisteredEvent) {
        this.aggregateId = event.aggregateId
    }

}
