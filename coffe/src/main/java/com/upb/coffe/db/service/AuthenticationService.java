package com.upb.coffe.db.service;

import com.upb.coffe.db.model.usuario.dto.UsuarioAuthenticationResponse;
import com.upb.coffe.rest.request.UsuarioRequest;

public interface AuthenticationService {
    UsuarioAuthenticationResponse authenticate(UsuarioRequest request);
}
