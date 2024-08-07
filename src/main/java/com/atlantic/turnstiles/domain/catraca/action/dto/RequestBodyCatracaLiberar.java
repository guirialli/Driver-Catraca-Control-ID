package com.atlantic.turnstiles.domain.catraca.action.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestBodyCatracaLiberar(
		@NotBlank @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$") String ip,
		@NotBlank String action, @NotBlank String parameters ) {

}
