package com.atlantic.turnstiles.domain.catraca.action.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActionsData(@JsonProperty("actions") ActionData[] actions) {

}
