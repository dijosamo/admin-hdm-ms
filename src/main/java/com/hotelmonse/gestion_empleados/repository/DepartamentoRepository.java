package com.hotelmonse.gestion_empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelmonse.gestion_empleados.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByNombre(String nombre);
}

