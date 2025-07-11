package com.challenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record RespuestaTopico (
        Long id,
        String nombre,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String autor,
        String curso
) {
}
