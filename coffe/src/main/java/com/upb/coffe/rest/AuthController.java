package com.upb.coffe.rest;

import com.upb.coffe.db.model.usuario.dto.UsuarioAuthenticationResponse;
import com.upb.coffe.db.service.AuthenticationService;
import com.upb.coffe.rest.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> register(@RequestBody UsuarioRequest request) {
       try {
           return ok(authenticationService.authenticate(request));
       }   catch (UsernameNotFoundException e){
           log.info("Error {}", e);

           Map<String, Object> responseBody = new HashMap<>();
           responseBody.put("mensaje", "Error en busqueda de usuarios");
           return ResponseEntity.badRequest().body(responseBody);
       }
        catch (Exception e){
           log.info("Error inesperado {}", e);

           Map<String, Object> responseBody = new HashMap<>();
           responseBody.put("mensaje", "Error en busqueda de usuarios");
           return ResponseEntity.badRequest().body(responseBody);
       }
    }
}
