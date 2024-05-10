package com.upb.coffe.db.service.impl;

import com.upb.coffe.db.model.usuario.dto.UsuarioAuthenticationResponse;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import com.upb.coffe.db.service.AuthenticationService;
import com.upb.coffe.rest.config.JwtService;
import com.upb.coffe.rest.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UsuarioAuthenticationResponse authenticate(UsuarioRequest request) {
        log.info("Llegaron los params de usuario");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getPassword()));

        var user = usuarioRepository.findByNombreAndEstadoFalse(request.getNombreUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var jwt = jwtService.generateToken(user);

        log.info("Intento de conexion usuario: {}",request.getNombreUsuario());

        return UsuarioAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
