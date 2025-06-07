package com.hotelmonse.gestion_empleados.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "EmpleadoDepartamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoDepartamento {

    @EmbeddedId
    private EmpleadoDepartamentoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empleadoId")
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departamentoId")
    private Departamento departamento;

    @Column(nullable = false)
    private LocalDate desde;

    private LocalDate hasta;
}
