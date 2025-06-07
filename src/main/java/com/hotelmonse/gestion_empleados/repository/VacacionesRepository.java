package com.hotelmonse.gestion_empleados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.Vacaciones;

@Repository
public interface VacacionesRepository extends JpaRepository<Vacaciones, Integer> {
    List<Vacaciones> findByEmpleadoId(Integer empleadoId);
    List<Vacaciones> findByEmpleadoIdAndAprobadasTrue(Integer empleadoId);
}
