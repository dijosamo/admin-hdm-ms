package com.hotelmonse.gestion_empleados.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelmonse.gestion_empleados.repository.UsuarioRepository;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<String> login(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPasswordHash()))
                .map(user -> jwtService.generarToken(user.getUsername()));
    }
    
    
}