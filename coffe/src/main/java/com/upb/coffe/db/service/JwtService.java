package com.upb.coffe.db.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public interface JwtService {
    HashMap<String, Object> generateToken(String user, String secret);
}
