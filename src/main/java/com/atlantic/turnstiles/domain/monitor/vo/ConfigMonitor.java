package com.atlantic.turnstiles.domain.monitor.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfigMonitor( @JsonProperty("monitor") ConfigHost monitor) {

}
