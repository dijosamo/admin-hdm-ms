package com.hotelmonse.gestion_empleados.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @Column(length = 50)
    private String puesto;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
