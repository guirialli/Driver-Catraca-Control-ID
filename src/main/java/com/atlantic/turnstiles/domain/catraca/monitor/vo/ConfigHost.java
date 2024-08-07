package com.atlantic.turnstiles.domain.catraca.monitor.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfigHost(@JsonProperty("hostname") String hostname,
		@JsonProperty("request_timeout") String request_timeout, @JsonProperty("port") String port,
		@JsonProperty("path") String path) {

}
