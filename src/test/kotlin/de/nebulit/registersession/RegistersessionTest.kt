package de.nebulit.registersession

import de.nebulit.common.Event
import de.nebulit.common.support.RandomData
import de.nebulit.domain.SessionAggregate
import de.nebulit.common.CommandException
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import de.nebulit.domain.commands.registersession.RegisterSessionCommand;
import de.nebulit.events.SessionRegisteredEvent
import java.util.UUID

class RegistersessionTest {

    private lateinit var fixture: FixtureConfiguration<SessionAggregate>

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(SessionAggregate::class.java)
    }

    @Test
    fun `RegistersessionTest`() {
        //GIVEN
        val events = mutableListOf<Event>()


        //WHEN
        val command = RegisterSessionCommand(
            aggregateId = UUID.fromString("7be572c6-3b10-4829-a614-04e524aef7fc"),
            abstract = RandomData.newInstance { },
            presenter = RandomData.newInstance { },
            topic = RandomData.newInstance { }
        )

        //THEN
        val expectedEvents = mutableListOf<Event>()

        expectedEvents.add(RandomData.newInstance<SessionRegisteredEvent> {
            this.aggregateId = command.aggregateId
            this.abstract = command.abstract
            this.presenter = command.presenter
            this.topic = command.topic
        })


        fixture.given(events)
            .`when`(command)
            .expectSuccessfulHandlerExecution()
            .expectEvents(*expectedEvents.toTypedArray())
    }

}
