package com.andramao.foro_alm.domain.respuesta;

import com.andramao.foro_alm.domain.topico.StatusTopico;
import com.andramao.foro_alm.domain.topico.Topico;
import com.andramao.foro_alm.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(columnDefinition = "TINYINT")
    private Boolean solucion = false;

    public void marcarComoSolucion() {
        this.solucion = true;
        this.topico.setStatus(StatusTopico.SOLUCIONADO);
    }


}