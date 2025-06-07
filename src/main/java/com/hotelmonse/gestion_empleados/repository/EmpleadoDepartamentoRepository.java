package com.hotelmonse.gestion_empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.EmpleadoDepartamento;
import com.hotelmonse.gestion_empleados.model.EmpleadoDepartamentoId;

@Repository
public interface EmpleadoDepartamentoRepository extends JpaRepository<EmpleadoDepartamento, EmpleadoDepartamentoId> {
    List<EmpleadoDepartamento> findByEmpleadoId(Integer empleadoId);
    List<EmpleadoDepartamento> findByDepartamentoId(Integer departamentoId);
}

// Repositorio para HorarioTipo
