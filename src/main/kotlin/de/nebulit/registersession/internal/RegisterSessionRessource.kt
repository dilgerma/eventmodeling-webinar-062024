package de.nebulit.registersession.internal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import de.nebulit.domain.commands.registersession.RegisterSessionCommand
import de.nebulit.common.CommandResult

import java.util.UUID;

import java.util.concurrent.CompletableFuture


data class RegistersessionPayload(
    var aggregateId: UUID,
    var abstract: String,
    var presenter: String,
    var topic: String
)

@RestController
class RegistersessionRessource(private var commandGateway: CommandGateway) {

    var logger = KotlinLogging.logger {}


    @CrossOrigin
    @PostMapping("/debug/registersession")
    fun processDebugCommand(
        @RequestParam aggregateId: UUID,
        @RequestParam abstract: String,
        @RequestParam presenter: String,
        @RequestParam topic: String
    ): CompletableFuture<CommandResult> {
        return commandGateway.send(
            RegisterSessionCommand(
                aggregateId,
                abstract,
                presenter,
                topic
            )
        )
    }


    @CrossOrigin
    @PostMapping("/registersession/{aggregateId}")
    fun processCommand(
        @PathVariable("aggregateId") aggregateId: UUID,
        @RequestBody payload: RegistersessionPayload
    ): CompletableFuture<CommandResult> {
        return commandGateway.send(
            RegisterSessionCommand(
                aggregateId = payload.aggregateId,
                abstract = payload.abstract,
                presenter = payload.presenter,
                topic = payload.topic
            )
        )
    }


}
