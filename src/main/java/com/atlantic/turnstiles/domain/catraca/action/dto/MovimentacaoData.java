package com.atlantic.turnstiles.domain.catraca.action.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimentacaoData(@NotNull @NotBlank String uidQrcode, @NotNull @NotBlank String idCracha,
		@NotNull @NotBlank String codigo, @NotNull @NotBlank String data, @NotNull @NotBlank String status,
		@NotNull Integer idDevice, @NotNull @NotBlank String sentido) {

}
