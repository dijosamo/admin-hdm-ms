package com.hotelmonse.gestion_empleados.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmonse.gestion_empleados.model.HorarioAsignado;
import com.hotelmonse.gestion_empleados.repository.HorarioAsignadoRepository;

@RestController
@RequestMapping("/api/horarios-asignados")
public class HorarioAsignadoController {

    private final HorarioAsignadoRepository horarioAsignadoRepository;

    public HorarioAsignadoController(HorarioAsignadoRepository horarioAsignadoRepository) {
        this.horarioAsignadoRepository = horarioAsignadoRepository;
    }

    @GetMapping
    public List<HorarioAsignado> listar() {
        return horarioAsignadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<HorarioAsignado> obtener(@PathVariable Integer id) {
        return horarioAsignadoRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioAsignado crear(@RequestBody HorarioAsignado horarioAsignado) {
        return horarioAsignadoRepository.save(horarioAsignado);
    }

    @PutMapping("/{id}")
    public HorarioAsignado actualizar(@PathVariable Integer id, @RequestBody HorarioAsignado horarioAsignado) {
        horarioAsignado.setId(id);
        return horarioAsignadoRepository.save(horarioAsignado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return horarioAsignadoRepository.findById(id)
                .map(h -> {
                    horarioAsignadoRepository.delete(h);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
