package com.hotelmonse.gestion_empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.VacacionesAnuales;

@Repository
public interface VacacionesAnualesRepository extends JpaRepository<VacacionesAnuales, Integer> {
    Optional<VacacionesAnuales> findByEmpleadoIdAndAño(Integer empleadoId, Integer año);
}

// Repositorio para Fichaje
