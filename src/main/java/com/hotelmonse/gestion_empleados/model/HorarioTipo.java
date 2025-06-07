package com.hotelmonse.gestion_empleados.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HorarioTipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(name = "hora_entrada_1", nullable = false)
    private LocalTime horaEntrada1;

    @Column(name = "hora_salida_1", nullable = false)
    private LocalTime horaSalida1;

    @Column(name = "hora_entrada_2")
    private LocalTime horaEntrada2;

    @Column(name = "hora_salida_2")
    private LocalTime horaSalida2;

    @Column(name = "es_turno_partido", nullable = false)
    private Boolean esTurnoPartido;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "horarioTipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<HorarioAsignado> asignados = new HashSet<>();
}
