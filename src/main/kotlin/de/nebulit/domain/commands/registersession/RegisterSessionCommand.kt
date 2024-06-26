package de.nebulit.domain.commands.registersession

import org.axonframework.modelling.command.TargetAggregateIdentifier
import de.nebulit.common.Command
import java.util.UUID;


data class RegisterSessionCommand(
    @TargetAggregateIdentifier override var aggregateId:UUID,
	var abstract:String,
	var presenter:String,
	var topic:String
): Command
