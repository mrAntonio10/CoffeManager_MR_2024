package com.upb.coffe.db.model.usuario.dto;

import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.enums.Roles;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsuarioDto {
    private Long id;
    private String nombreUsuario;
    private String nombreCompleto;
    private String rol;
    private Boolean estado;

    public UsuarioDto(Usuario u) {
        this.id = u.getId();
        this.nombreUsuario = u.getNombreUsuario();
        this.nombreCompleto = u.getNombre()+ " " + u.getApellido();
        this.estado = u.getEstado();
    }
}
