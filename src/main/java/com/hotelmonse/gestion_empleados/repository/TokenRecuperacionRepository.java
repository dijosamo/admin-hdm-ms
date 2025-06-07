package com.hotelmonse.gestion_empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmonse.gestion_empleados.model.TokenRecuperacion;

public interface TokenRecuperacionRepository extends JpaRepository<TokenRecuperacion, Integer> {

    Optional<TokenRecuperacion> findByToken(String token);
}
