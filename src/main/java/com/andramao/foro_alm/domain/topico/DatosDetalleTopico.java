package com.andramao.foro_alm.domain.topico;

import com.andramao.foro_alm.domain.respuesta.DatosDetalleRespuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        StatusTopico status,
        String nombreAutor,
        String nombreCurso,
        List<DatosDetalleRespuesta>respuestas
) {
    // Constructor compacto para facilitar el mapeo desde la entidad
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getFechaActualizacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getRespuestas().stream() // Convertimos las entidades a DTOs
                        .map(DatosDetalleRespuesta::new)
                        .toList()
        );
    }
}