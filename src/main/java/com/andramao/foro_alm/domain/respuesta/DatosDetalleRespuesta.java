package com.andramao.foro_alm.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long autorId,
        String nombreAutor,
        Boolean solucion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion()
        );
    }
}
