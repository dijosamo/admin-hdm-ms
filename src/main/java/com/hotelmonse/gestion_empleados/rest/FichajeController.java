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

import com.hotelmonse.gestion_empleados.model.Fichaje;
import com.hotelmonse.gestion_empleados.repository.FichajeRepository;

@RestController
@RequestMapping("/api/fichajes")
public class FichajeController {

    private final FichajeRepository fichajeRepository;

    public FichajeController(FichajeRepository fichajeRepository) {
        this.fichajeRepository = fichajeRepository;
    }

    @GetMapping
    public List<Fichaje> listar() {
        return fichajeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Fichaje> obtener(@PathVariable Integer id) {
        return fichajeRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fichaje crear(@RequestBody Fichaje fichaje) {
        return fichajeRepository.save(fichaje);
    }

    @PutMapping("/{id}")
    public Fichaje actualizar(@PathVariable Integer id, @RequestBody Fichaje fichaje) {
        fichaje.setId(id);
        return fichajeRepository.save(fichaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return fichajeRepository.findById(id)
                .map(f -> {
                    fichajeRepository.delete(f);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
