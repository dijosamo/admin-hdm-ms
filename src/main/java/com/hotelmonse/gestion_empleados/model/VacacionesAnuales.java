package com.hotelmonse.gestion_empleados.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VacacionesAnuales",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"empleado_id", "año"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacacionesAnuales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(name = "año", nullable = false)
    private Integer año;

    @Column(name = "dias_totales", nullable = false)
    private Integer diasTotales;

    @Column(name = "dias_usados")
    @Builder.Default
    private Integer diasUsados = 0;

    @Column(columnDefinition = "TEXT")
    private String comentarios;
}
