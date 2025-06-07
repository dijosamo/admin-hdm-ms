package com.hotelmonse.gestion_empleados.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDepartamentoId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer empleadoId;
    private Integer departamentoId;
    
}
