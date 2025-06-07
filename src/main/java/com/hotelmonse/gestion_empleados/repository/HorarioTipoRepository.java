package com.hotelmonse.gestion_empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.HorarioTipo;

@Repository
public interface HorarioTipoRepository extends JpaRepository<HorarioTipo, Integer> {
    Optional<HorarioTipo> findByNombre(String nombre);
}

// Repositorio para HorarioAsignado
