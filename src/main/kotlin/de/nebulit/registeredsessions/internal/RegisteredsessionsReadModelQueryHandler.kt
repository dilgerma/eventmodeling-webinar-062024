package de.nebulit.registeredsessions.internal

import de.nebulit.registeredsessions.RegisteredsessionsReadModel
import org.springframework.stereotype.Component
import de.nebulit.registeredsessions.internal.RegisteredsessionsReadModelRepository
import org.axonframework.queryhandling.QueryHandler
import de.nebulit.registeredsessions.RegisteredsessionsReadModelQuery
import java.util.UUID;


@Component
class RegisteredsessionsReadModelQueryHandler(private val repository:RegisteredsessionsReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: RegisteredsessionsReadModelQuery): RegisteredsessionsReadModel? {
      return RegisteredsessionsReadModel(repository.findAll())
  }

}
