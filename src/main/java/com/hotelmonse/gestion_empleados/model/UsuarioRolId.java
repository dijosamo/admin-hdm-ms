package com.hotelmonse.gestion_empleados.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRolId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "rol_id")
    private Integer rolId;
    

    // equals y hashCode obligatorios para clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRolId)) return false;
        UsuarioRolId that = (UsuarioRolId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(rolId, that.rolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, rolId);
    }
}

