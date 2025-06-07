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

import com.hotelmonse.gestion_empleados.model.Departamento;
import com.hotelmonse.gestion_empleados.repository.DepartamentoRepository;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoController(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @GetMapping
    public List<Departamento> listar() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Departamento> obtener(@PathVariable Integer id) {
        return departamentoRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento crear(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(@PathVariable Integer id, @RequestBody Departamento departamento) {
        departamento.setId(id);
        return departamentoRepository.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return departamentoRepository.findById(id)
                .map(dep -> {
                    departamentoRepository.delete(dep);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
