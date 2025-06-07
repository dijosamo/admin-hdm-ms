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

import com.hotelmonse.gestion_empleados.model.Rol;
import com.hotelmonse.gestion_empleados.repository.RolRepository;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @GetMapping
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtener(@PathVariable Integer id) {
        Optional<Rol> rol = rolRepository.findById(id);
        return rol.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Rol crear(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Integer id, @RequestBody Rol rol) {
        return rolRepository.findById(id)
                .map(existingRol -> {
                    rol.setId(id);
                    Rol actualizado = rolRepository.save(rol);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
        return rolRepository.findById(id)
                .map(rol -> {
                    rolRepository.delete(rol);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
