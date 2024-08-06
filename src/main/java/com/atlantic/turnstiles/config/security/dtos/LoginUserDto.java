package com.atlantic.turnstiles.config.security.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDto(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {

}
