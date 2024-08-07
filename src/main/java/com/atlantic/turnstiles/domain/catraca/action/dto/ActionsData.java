package com.atlantic.turnstiles.domain.catraca.action.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActionsData(@JsonProperty("actions") List<ActionData> actions) {

}
