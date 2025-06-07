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

import com.hotelmonse.gestion_empleados.model.VacacionesAnuales;
import com.hotelmonse.gestion_empleados.repository.VacacionesAnualesRepository;

@RestController
@RequestMapping("/api/vacaciones-anuales")
public class VacacionesAnualesController {

    private final VacacionesAnualesRepository vacacionesAnualesRepository;

    public VacacionesAnualesController(VacacionesAnualesRepository vacacionesAnualesRepository) {
        this.vacacionesAnualesRepository = vacacionesAnualesRepository;
    }

    @GetMapping
    public List<VacacionesAnuales> listar() {
        return vacacionesAnualesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<VacacionesAnuales> obtener(@PathVariable Integer id) {
        return vacacionesAnualesRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacacionesAnuales crear(@RequestBody VacacionesAnuales vacacionesAnuales) {
        return vacacionesAnualesRepository.save(vacacionesAnuales);
    }

    @PutMapping("/{id}")
    public VacacionesAnuales actualizar(@PathVariable Integer id, @RequestBody VacacionesAnuales vacacionesAnuales) {
        vacacionesAnuales.setId(id);
        return vacacionesAnualesRepository.save(vacacionesAnuales);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return vacacionesAnualesRepository.findById(id)
                .map(v -> {
                    vacacionesAnualesRepository.delete(v);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
