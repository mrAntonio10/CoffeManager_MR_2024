package com.upb.coffe.db.model.usuario.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;
    private String nombreUsuario;
    private String nombreCompleto;
    private String rol;
    private Boolean estado;
}
