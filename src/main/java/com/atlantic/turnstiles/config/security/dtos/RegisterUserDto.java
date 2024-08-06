package com.atlantic.turnstiles.config.security.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDto(@NotNull @NotBlank String username, @NotNull @NotBlank @Length(min = 8) String password) {

}
