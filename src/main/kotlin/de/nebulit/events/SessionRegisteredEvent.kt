package de.nebulit.events

import de.nebulit.common.Event

import java.util.UUID;


data class SessionRegisteredEvent(
    var aggregateId:UUID,
	var abstract:String,
	var presenter:String,
	var topic:String
) : Event
