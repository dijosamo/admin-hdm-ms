package com.hotelmonse.gestion_empleados.service;

import java.util.List;
import java.util.Optional;

import com.hotelmonse.gestion_empleados.model.Empleado;

public interface EmpleadoService {
    List<Empleado> listarTodos();
    Optional<Empleado> obtenerPorId(Integer id);
    Empleado guardar(Empleado empleado);
    void eliminar(Integer id);
}
