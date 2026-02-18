package com.andramao.foro_alm.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor,

        @NotNull(message = "El ID del curso es obligatorio")
        Long idCurso
) {
}