package com.hotelmonse.gestion_empleados.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Fichaje",
       indexes = {@Index(name = "idx_empleado_fecha", columnList = "empleado_id, fecha")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fichaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoFichaje tipo;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(name = "turno_parcial", nullable = false)
    @Builder.Default
    private Integer turnoParcial = 1;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}

enum TipoFichaje {
    entrada,
    salida
}
