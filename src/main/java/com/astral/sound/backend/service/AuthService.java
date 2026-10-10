package com.astral.sound.backend.service;

import com.astral.sound.backend.model.Usuario;
import com.astral.sound.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario registrarUsuario(Usuario usuario) {
        // Encriptamos la contraseña antes de guardarla en la base de datos
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // Por defecto, si no se especifica rol, se asigna como OYENTE
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("OYENTE");
        }
        
        return usuarioRepository.save(usuario);
    }
}
