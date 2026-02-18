package com.andramao.foro_alm.service;

import com.andramao.foro_alm.domain.curso.CursoRepository;
import com.andramao.foro_alm.domain.topico.*;
import com.andramao.foro_alm.domain.usuario.UsuarioRepository;
import com.andramao.foro_alm.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos) {

        // 1. Regla de negocio: Validar duplicados (título y mensaje iguales)
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje.");
        }

        // 2. Validar que el autor existe
        if (!usuarioRepository.existsById(datos.idAutor())) {
            throw new ValidacionException("El ID del autor proporcionado no existe.");
        }

        // 3. Validar que el curso existe
        if (!cursoRepository.existsById(datos.idCurso())) {
            throw new ValidacionException("El ID del curso proporcionado no existe.");
        }

        // 4. Obtener las entidades completas
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        // 5. Crear la entidad Topico usando el constructor que definimos antes
        var topico = new Topico(datos, autor, curso);

        // 6. Guardar en la base de datos
        topicoRepository.save(topico);

        // 7. Retornar el DTO con los detalles del tópico creado
        return new DatosDetalleTopico(topico);
    }
}