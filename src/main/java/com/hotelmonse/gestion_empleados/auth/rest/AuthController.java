package com.hotelmonse.gestion_empleados.auth.rest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hotelmonse.gestion_empleados.auth.LoginRequest;
import com.hotelmonse.gestion_empleados.auth.LoginResponse;
import com.hotelmonse.gestion_empleados.auth.LoginService;
import com.hotelmonse.gestion_empleados.model.TokenRecuperacion;
import com.hotelmonse.gestion_empleados.model.Usuario;
import com.hotelmonse.gestion_empleados.repository.TokenRecuperacionRepository;
import com.hotelmonse.gestion_empleados.repository.UsuarioRepository;
import com.hotelmonse.gestion_empleados.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginService loginService;
    
    private final TokenRecuperacionRepository tokenRecuperacionRepository;
    
    private UsuarioService usuarioService;
    
    private PasswordEncoder passwordEncoder;
    
    private UsuarioRepository usuarioRepository;

    public AuthController(LoginService loginService, TokenRecuperacionRepository tokenRecuperacionRepository, UsuarioService usuarioService
    		, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.loginService = loginService;
        this.tokenRecuperacionRepository = tokenRecuperacionRepository;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return loginService.login(request.getUsername(), request.getPassword())
                .map(token -> ResponseEntity.ok(new LoginResponse(true, "Login exitoso", token)))
                .orElseGet(() -> ResponseEntity.status(401)
                        .body(new LoginResponse(false, "Credenciales inválidas", null)));
    }
    
    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestParam Integer empleadoId,
                                                   @RequestParam String username,
                                                   @RequestParam String email) {
        String url = usuarioService.crearUsuarioYGenerarToken(empleadoId, username, email);
        return ResponseEntity.ok("Usuario creado. Enlace para establecer contraseña: " + url);
    }
    
    @PostMapping("/establecer-contraseña")
    public ResponseEntity<?> establecerPassword(@RequestParam String token, @RequestBody String nuevaPassword) {
        TokenRecuperacion tr = tokenRecuperacionRepository.findByToken(token)
            .filter(t -> !t.isUsado() && t.getExpiracion().isAfter(LocalDateTime.now()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido o expirado"));

        Usuario usuario = tr.getUsuario();
        usuario.setPasswordHash(passwordEncoder.encode(nuevaPassword));
        usuario.setActivo(true); // si lo necesitas
        tr.setUsado(true);
        usuarioRepository.save(usuario);
        tokenRecuperacionRepository.save(tr);

        return ResponseEntity.ok("Contraseña establecida correctamente.");
    }
}