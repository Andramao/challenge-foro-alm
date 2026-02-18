package com.andramao.foro_alm.domain.topico;

import com.andramao.foro_alm.domain.curso.Curso;
import com.andramao.foro_alm.domain.respuesta.Respuesta;
import com.andramao.foro_alm.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Setter
@Getter // <--- ESTA ES LA QUE GENERA LOS MÉTODOS getId(), getTitulo(), etc.
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion") // Esto mapea a la columna de la DB
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas = new ArrayList<>();

    public void actualizarDatos(DatosActualizarTopico datosActualizar) {
        boolean modificado = false;

        if (datosActualizar.titulo() != null) {
            this.titulo = datosActualizar.titulo();
            modificado = true;
        }
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
            modificado = true;
        }

        if (modificado) {
            this.fechaActualizacion = LocalDateTime.now();
        }
    }

    public void eliminar() {
        this.status = StatusTopico.CERRADO;
    }

    public void cerrarTopico() {
        this.status = StatusTopico.CERRADO;
    }


    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.NO_RESPONDIDO;
    }

}
