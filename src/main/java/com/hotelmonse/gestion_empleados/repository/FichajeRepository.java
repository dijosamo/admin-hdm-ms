package com.hotelmonse.gestion_empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.Fichaje;

@Repository
public interface FichajeRepository extends JpaRepository<Fichaje, Integer> {
    List<Fichaje> findByEmpleadoIdAndFecha(Integer empleadoId, java.time.LocalDate fecha);
}

// Repositorio para Usuario
