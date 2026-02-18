package com.andramao.foro_alm.infra.security;

import com.andramao.foro_alm.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("ForoHub_Alura")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        // El token expira en 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token nulo");
        }
        try {
            Algorithm algoritmo = Algorithm.HMAC256(apiSecret);
            return JWT.require(algoritmo)
                    .withIssuer("ForoHub_Alura")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido o expirado");
        }
    }
}
