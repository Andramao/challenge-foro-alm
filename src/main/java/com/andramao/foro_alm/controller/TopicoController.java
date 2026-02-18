package com.andramao.foro_alm.controller;

import com.andramao.foro_alm.domain.topico.*;
import com.andramao.foro_alm.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.andramao.foro_alm.domain.topico.TopicoRepository;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                              UriComponentsBuilder uriBuilder) {
        var detalleTopico = service.registrar(datos);
        var url = uriBuilder.path("/topicos/{id}").buildAndExpand(detalleTopico.id()).toUri();
        return ResponseEntity.created(url).body(detalleTopico);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de tópicos", description = "Lista todos los tópicos que no tengan status CERRADO", tags = { "consulta" })
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion) {

        var pagina = repository.findAllByStatusNot(StatusTopico.CERRADO, paginacion)
                .map(DatosListadoTopico::new);
        return ResponseEntity.ok(pagina);
    }
    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        // Cambiamos a findById para manejar el 404 si no existe
        var topico = repository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizar) {
        // 1. Verificamos si existe antes de intentar cargar la referencia
        if (!repository.existsById(datosActualizar.id())) {
            return ResponseEntity.notFound().build();
        }

        // 2. Cargamos la entidad
        var topico = repository.getReferenceById(datosActualizar.id());

        // 3. Ejecutamos la lógica de actualización definida en la Entidad
        topico.actualizarDatos(datosActualizar);

        // 4. Retornamos el detalle actualizado
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            topico.eliminar(); // Cambia el status a CERRADO
            return ResponseEntity.noContent().build(); // Retorna 204
        }

        return ResponseEntity.notFound().build();
    }


}