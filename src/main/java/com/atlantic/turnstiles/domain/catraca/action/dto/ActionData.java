package com.atlantic.turnstiles.domain.catraca.action.dto;

import jakarta.validation.constraints.NotBlank;

public record ActionData(@NotBlank String action, @NotBlank String parameters) {

}
