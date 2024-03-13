package com.upb.coffe.rest.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    private String nombreUsuario;

    private String password;


}


