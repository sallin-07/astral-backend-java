package com.astral.sound.backend.controller;

import com.astral.sound.backend.model.Usuario;
import com.astral.sound.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = authService.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }
}
