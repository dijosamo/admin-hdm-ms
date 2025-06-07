package com.hotelmonse.gestion_empleados.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "HorarioAsignado",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"empleado_id", "fecha"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horario_tipo_id", nullable = false)
    private HorarioTipo horarioTipo;

    @Column(nullable = false)
    private LocalDate fecha;
}
