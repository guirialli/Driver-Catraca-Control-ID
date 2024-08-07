package com.atlantic.turnstiles.domain.catraca.monitor.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfigMonitor( @JsonProperty("monitor") ConfigHost monitor) {

}
