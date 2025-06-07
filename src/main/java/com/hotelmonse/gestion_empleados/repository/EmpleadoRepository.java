package com.hotelmonse.gestion_empleados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.Empleado;

// Repositorio para Empleado
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByDni(String dni);
    List<Empleado> findByActivoTrue();
}

// Repositorio para Departamento
