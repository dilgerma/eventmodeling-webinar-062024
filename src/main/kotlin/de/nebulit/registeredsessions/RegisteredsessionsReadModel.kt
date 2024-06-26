package de.nebulit.registeredsessions

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID;


class RegisteredsessionsReadModelQuery()

@Entity
class RegisteredsessionsReadModelEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "aggregateId")
    var aggregateId: UUID? = null;
    @Column(name = "abstract")
    var abstract: String? = null;
    @Column(name = "presenter")
    var presenter: String? = null;
    @Column(name = "topic")
    var topic: String? = null;
}

data class RegisteredsessionsReadModel(val data: List<RegisteredsessionsReadModelEntity>)
