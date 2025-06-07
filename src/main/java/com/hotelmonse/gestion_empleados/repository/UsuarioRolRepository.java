package com.hotelmonse.gestion_empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.UsuarioRol;
import com.hotelmonse.gestion_empleados.model.UsuarioRolId;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {
    List<UsuarioRol> findByUsuarioId(Integer usuarioId);
    List<UsuarioRol> findByRolId(Integer rolId);
}
