package com.andramao.foro_alm.controller;

import com.andramao.foro_alm.domain.respuesta.DatosDetalleRespuesta;
import com.andramao.foro_alm.domain.respuesta.DatosRegistroRespuesta;
import com.andramao.foro_alm.domain.respuesta.Respuesta;
import com.andramao.foro_alm.domain.respuesta.RespuestaRepository;
import com.andramao.foro_alm.domain.topico.DatosDetalleTopico;
import com.andramao.foro_alm.domain.topico.TopicoRepository;
import com.andramao.foro_alm.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos/{topicoId}/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@PathVariable Long topicoId, @RequestBody @Valid DatosRegistroRespuesta datos) {
        var topico = topicoRepository.getReferenceById(topicoId);
        var autor = usuarioRepository.getReferenceById(datos.autorId());

        var respuesta = new Respuesta(null, datos.mensaje(), topico, LocalDateTime.now(), autor, false);
        respuestaRepository.save(respuesta);

        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> retornarDatosDetalleTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @PutMapping("/{id}/solucion")
    @Transactional
    public ResponseEntity marcarSolucion(@PathVariable Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuesta.marcarComoSolucion();
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }
}