package com.andramao.foro_alm.domain.topico;

import com.andramao.foro_alm.domain.topico.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Este método lo usamos en el Service para evitar duplicados
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Aprovechando, para el listado ordenado por fecha
    //Page<Topico> findAll(Pageable paginacion);


    // Este método permite listar solo los tópicos activos al usar borrado lógico
    Page<Topico> findAllByStatusNot(StatusTopico status, Pageable paginacion);
}