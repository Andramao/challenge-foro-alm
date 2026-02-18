package com.andramao.foro_alm.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Aquí puedes añadir métodos de búsqueda personalizados más adelante si es necesario
}