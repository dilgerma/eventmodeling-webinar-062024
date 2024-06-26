package de.nebulit.registeredsessions.integration

import de.nebulit.common.CommandResult
import de.nebulit.common.support.BaseIntegrationTest
import de.nebulit.common.support.RandomData
import de.nebulit.common.support.awaitUntilAssserted
import de.nebulit.domain.commands.registersession.RegisterSessionCommand
import de.nebulit.registeredsessions.RegisteredsessionsReadModelQuery
import de.nebulit.registeredsessions.RegisteredsessionsReadModel
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import java.util.*

class RegisteredsessionsTestIntegration : BaseIntegrationTest() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `RegisteredsessionsTest`() {


        val aggregateId = UUID.randomUUID()

        var registerSessionCommand = RandomData.newInstance<RegisterSessionCommand> {
            this.aggregateId = aggregateId
        }

        var registerSessionCommandResult = commandGateway.sendAndWait<CommandResult>(registerSessionCommand)



        awaitUntilAssserted {
            var readModel =
                queryGateway.query(RegisteredsessionsReadModelQuery(), RegisteredsessionsReadModel::class.java)
            assertThat(readModel.get().data).isNotNull
        }


    }

}
