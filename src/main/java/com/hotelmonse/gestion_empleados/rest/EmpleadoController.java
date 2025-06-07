package com.hotelmonse.gestion_empleados.rest;

import java.util.List;

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

import com.hotelmonse.gestion_empleados.model.Empleado;
import com.hotelmonse.gestion_empleados.service.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "Gestión de empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los empleados")
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        return empleadoService.obtenerPorId(id)
                .map(e -> {
                    empleado.setId(id);
                    Empleado actualizado = empleadoService.guardar(empleado);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarEmpleado(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id)
                .map(e -> {
                    empleadoService.eliminar(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
