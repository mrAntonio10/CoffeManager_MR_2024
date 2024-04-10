package com.upb.coffe.db.service.impl;


import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import com.upb.coffe.db.service.JwtService;
import com.upb.coffe.db.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    private static final long EXPIRATION_TIME = 7_200_000;


    public JwtServiceImpl(UsuarioRepository usuarioRepository) {}

    @Override
    public HashMap<String, Object> generateToken(String user, String secret) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(user)
                .setIssuer(user)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secret))
                .compact();

        HashMap<String, Object> resp = new HashMap<>();
            resp.put("bearer", token);
        return resp;
    }
}

