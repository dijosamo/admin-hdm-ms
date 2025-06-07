package com.hotelmonse.gestion_empleados.rest;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmonse.gestion_empleados.model.HorarioAsignado;
import com.hotelmonse.gestion_empleados.service.impl.HorarioService;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping("/empleado/{empleadoId}/mes/{anio}/{mes}")
    public ResponseEntity<List<HorarioAsignado>> getHorariosEmpleadoMes(
            @PathVariable Integer empleadoId,
            @PathVariable int anio,
            @PathVariable int mes) {
        YearMonth ym;
        try {
            ym = YearMonth.of(anio, mes);
        } catch (DateTimeException e) {
            return ResponseEntity.badRequest().build();
        }
        List<HorarioAsignado> horarios = horarioService.obtenerHorariosEmpleadoMes(empleadoId, ym);
        return ResponseEntity.ok(horarios);
    }
}
