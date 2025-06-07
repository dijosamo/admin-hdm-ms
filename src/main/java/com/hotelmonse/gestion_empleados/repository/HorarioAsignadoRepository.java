package com.hotelmonse.gestion_empleados.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.HorarioAsignado;

@Repository
public interface HorarioAsignadoRepository extends JpaRepository<HorarioAsignado, Integer> {
	
    List<HorarioAsignado> findByEmpleadoId(Integer empleadoId);
    
    Optional<HorarioAsignado> findByEmpleadoIdAndFecha(Integer empleadoId, java.time.LocalDate fecha);
    
    List<HorarioAsignado> findByEmpleadoIdAndFechaBetween(Integer empleadoId, LocalDate inicio, LocalDate fin);
}

// Repositorio para Vacaciones
