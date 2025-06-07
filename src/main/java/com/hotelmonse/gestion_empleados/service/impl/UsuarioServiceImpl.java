package com.hotelmonse.gestion_empleados.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelmonse.gestion_empleados.model.Empleado;
import com.hotelmonse.gestion_empleados.model.TokenRecuperacion;
import com.hotelmonse.gestion_empleados.model.Usuario;
import com.hotelmonse.gestion_empleados.repository.EmpleadoRepository;
import com.hotelmonse.gestion_empleados.repository.TokenRecuperacionRepository;
import com.hotelmonse.gestion_empleados.repository.UsuarioRepository;
import com.hotelmonse.gestion_empleados.service.EmailService;
import com.hotelmonse.gestion_empleados.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenRecuperacionRepository tokenRecuperacionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

    private static final int EXPIRACION_HORAS = 24;

    public Optional<Usuario> obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public String crearUsuarioYGenerarToken(Integer empleadoId, String username, String email) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setEmpleado(empleado);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setActivo(false); // Inactivo hasta que establezca contraseña
        usuario.setPasswordHash(passwordEncoder.encode(UUID.randomUUID().toString())); // Temporal oculta
        usuarioRepository.save(usuario);

        // Generar token de recuperación
        String token = UUID.randomUUID().toString();
        TokenRecuperacion tr = new TokenRecuperacion();
        tr.setUsuario(usuario);
        tr.setToken(token);
        tr.setExpiracion(LocalDateTime.now().plusHours(EXPIRACION_HORAS));
        tokenRecuperacionRepository.save(tr);
        
        String link = "https://tuapp.com/establecer-password?token=" + token;
        String mensaje = "Hola, para activar tu cuenta y establecer la contraseña, haz click en el siguiente enlace:\n" + link;
        emailService.enviarEmail(email, "Activación de cuenta", mensaje);

        return "https://tuhotel.com/establecer-contraseña?token=" + token;
    }
}
