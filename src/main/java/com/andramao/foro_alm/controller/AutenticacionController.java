package com.andramao.foro_alm.controller;

import com.andramao.foro_alm.domain.usuario.DatosAutenticacionUsuario;
import com.andramao.foro_alm.domain.usuario.Usuario;
import com.andramao.foro_alm.infra.security.DatosJWTToken;
import com.andramao.foro_alm.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        // 1. Crear el token de autenticación de Spring con los datos del DTO
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.email(),
                datos.contrasena()
        );

        // 2. El Manager valida si el usuario existe y la contraseña es correcta
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // 3. Si es válido, generamos el JWT
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // 4. Retornamos el token al cliente
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
