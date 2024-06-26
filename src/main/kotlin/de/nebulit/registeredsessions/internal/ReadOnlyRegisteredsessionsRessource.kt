package de.nebulit.registeredsessions.internal

import de.nebulit.registeredsessions.RegisteredsessionsReadModel
import de.nebulit.registeredsessions.RegisteredsessionsReadModelQuery
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture
import java.util.UUID;


@RestController
class RegisteredsessionsRessource(
    private var queryGateway: QueryGateway
) {

    var logger = KotlinLogging.logger {}

    @CrossOrigin
    @GetMapping("/registeredsessions")
    fun findReadModel(): CompletableFuture<RegisteredsessionsReadModel> {
        return queryGateway.query(RegisteredsessionsReadModelQuery(), RegisteredsessionsReadModel::class.java)
    }

}
