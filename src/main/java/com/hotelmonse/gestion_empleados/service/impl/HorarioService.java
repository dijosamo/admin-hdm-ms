package com.hotelmonse.gestion_empleados.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelmonse.gestion_empleados.model.HorarioAsignado;
import com.hotelmonse.gestion_empleados.repository.HorarioAsignadoRepository;

@Service
public class HorarioService {

    private final HorarioAsignadoRepository horarioAsignadoRepository;

    public HorarioService(HorarioAsignadoRepository horarioAsignadoRepository) {
        this.horarioAsignadoRepository = horarioAsignadoRepository;
    }

    public List<HorarioAsignado> obtenerHorariosEmpleadoMes(Integer empleadoId, YearMonth mes) {
        LocalDate inicio = mes.atDay(1);
        LocalDate fin = mes.atEndOfMonth();
        return horarioAsignadoRepository.findByEmpleadoIdAndFechaBetween(empleadoId, inicio, fin);
    }
}	