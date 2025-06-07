package com.hotelmonse.gestion_empleados.service;

import java.util.Optional;

import com.hotelmonse.gestion_empleados.model.Usuario;

public interface UsuarioService {

    Optional<Usuario> obtenerPorUsername(String username);

    String crearUsuarioYGenerarToken(Integer empleadoId, String username, String email);
}
