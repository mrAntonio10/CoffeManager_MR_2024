package com.upb.coffe.rest.config;

import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        log.info("Se filtra algo");
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("no hay xd");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("pasa el jtw");
        jwt = authHeader.substring(7); //Bearer y el espacio son 7 posiciones
            username = jwtService.extractUsername(jwt);
            log.info("extrae user [{}]", username);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails usuario = this.userDetailsService.loadUserByUsername(username);

            log.info("pasa el bean userdetailservice {}");

            if(jwtService.isTokenValid(jwt, usuario)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("El toquen fue valido chicossss");
            }
            log.info("fuera1");
        }
        log.info("fuera2 [{}], [{}]", request.getServletPath(), response.getStatus());
        filterChain.doFilter(request, response);
    }
}
