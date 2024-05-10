package com.upb.coffe.rest;

import com.upb.coffe.db.service.AuthenticationService;
import com.upb.coffe.rest.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8084"}, allowCredentials = "true", methods = {RequestMethod.GET})
public class CsrfController {

    @GetMapping("csrf/token")
    public CsrfToken csrf(CsrfToken token) {
        log.info("Se solicito token csrf");
        return token;
    }
}
