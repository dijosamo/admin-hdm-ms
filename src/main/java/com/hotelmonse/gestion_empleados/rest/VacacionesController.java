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

import com.hotelmonse.gestion_empleados.model.Vacaciones;
import com.hotelmonse.gestion_empleados.repository.VacacionesRepository;

@RestController
@RequestMapping("/api/vacaciones")
public class VacacionesController {

    private final VacacionesRepository vacacionesRepository;

    public VacacionesController(VacacionesRepository vacacionesRepository) {
        this.vacacionesRepository = vacacionesRepository;
    }

    @GetMapping
    public List<Vacaciones> listar() {
        return vacacionesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vacaciones> obtener(@PathVariable Integer id) {
        return vacacionesRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vacaciones crear(@RequestBody Vacaciones vacaciones) {
        return vacacionesRepository.save(vacaciones);
    }

    @PutMapping("/{id}")
    public Vacaciones actualizar(@PathVariable Integer id, @RequestBody Vacaciones vacaciones) {
        vacaciones.setId(id);
        return vacacionesRepository.save(vacaciones);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return vacacionesRepository.findById(id)
                .map(v -> {
                    vacacionesRepository.delete(v);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
