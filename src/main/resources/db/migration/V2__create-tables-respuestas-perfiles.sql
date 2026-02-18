CREATE TABLE perfiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion TINYINT NOT NULL DEFAULT 0,
    autor_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_respuestas_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id),
    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos (id)
);


CREATE TABLE usuario_perfiles (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario_perfiles_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_usuario_perfiles_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfiles (id)
);