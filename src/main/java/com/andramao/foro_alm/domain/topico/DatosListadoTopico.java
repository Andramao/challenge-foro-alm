package com.andramao.foro_alm.domain.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion)
{
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString());
    }
}
