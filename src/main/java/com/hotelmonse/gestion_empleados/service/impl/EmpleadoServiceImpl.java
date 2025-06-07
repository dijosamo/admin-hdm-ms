package com.hotelmonse.gestion_empleados.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotelmonse.gestion_empleados.model.Empleado;
import com.hotelmonse.gestion_empleados.repository.EmpleadoRepository;
import com.hotelmonse.gestion_empleados.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
