package com.challenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull LocalDateTime fechaDeCreacion,
        @NotBlank String status,
        @NotBlank String autor,
        @NotBlank String curso
) {
}
