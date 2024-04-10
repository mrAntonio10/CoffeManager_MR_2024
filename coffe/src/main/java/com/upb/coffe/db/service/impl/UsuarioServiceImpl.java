package com.upb.coffe.db.service.impl;


import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import com.upb.coffe.db.service.JwtService;
import com.upb.coffe.db.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtTokenService;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = new BCryptPasswordEncoder(10);;
        this.jwtTokenService = jwtService;
    }

    @Override
    @Transactional
    public Long save(Usuario usuarioDto) {
        if (usuarioDto.getId() == null) {
            Usuario usuario = new Usuario();
                usuario.setId(null);
                usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
                usuario.setPassword(encoder.encode(usuarioDto.getPassword()));
                usuario.setNombre(usuarioDto.getNombre());
                usuario.setApellido(usuarioDto.getApellido());
                usuario.setEstado(usuarioDto.getEstado());
                usuario.setRol(usuarioDto.getRol());

            usuario = usuarioRepository.save(usuario);
            return usuario.getId();
        } else {    //modificar usuario
            Optional<Usuario> usuarioOpt = usuarioRepository.findByIdAndEstadoFalse(usuarioDto.getId());
            if(!usuarioOpt.isPresent()){
                throw new NoSuchElementException("Usuario no encontrada");
            }
            Usuario usuario = usuarioOpt.get();

            usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
            usuario.setPassword(encoder.encode(usuarioDto.getPassword()));
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setApellido(usuarioDto.getApellido());
            usuario.setEstado(usuarioDto.getEstado());
            usuario = usuarioRepository.save(usuario);
            return usuario.getId();

        }
    }

    @Override
    public HashMap<String, Object> findByUsernameAndPassword(String nombreUsuario, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreAndEstadoFalse(nombreUsuario);

        log.info("[{}]", usuarioOpt);
        if(!usuarioOpt.isPresent()){
            throw new NoSuchElementException("Las credenciales no pertenecen a un usuario dentro del sistema");
        }

        if(!encoder.matches(password, usuarioOpt.get().getPassword())) {
            throw new NoSuchElementException("Las credenciales no pertenecen a un usuario dentro del sistema");
        }
        HashMap<String, Object> resp =  new HashMap<>();
            resp.put("consumer",usuarioOpt.get().getConsumer() );

        return resp;
    }

    @Override
    public List<UsuarioDto> findAllUsers() {
        List<Usuario> userList = usuarioRepository.findAll();
        List<UsuarioDto> resp = new ArrayList<>();

        for (Usuario usr: userList) {
            UsuarioDto dto = UsuarioDto.builder()
                    .id(usr.getId())
                    .nombreUsuario(usr.getNombreUsuario())
                    .nombreCompleto(usr.getNombre() +" "+ usr.getApellido())
                    .rol(usr.getRol().toString())
                    .estado(usr.getEstado())
                    .build();

            resp.add(dto);
        }
        return resp;
    }

    @Override
    public Long deleteUsuario(Long id) {
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findById(id);
        if(!usuarioOpt.isPresent()) {
            throw new NoSuchElementException("Usuario con id: " + id + "no encontrado");
        }
        usuarioOpt.get().setEstado(true);
        Usuario usuarioResp = usuarioOpt.get();
            usuarioResp.setEstado(true);
        usuarioResp = this.usuarioRepository.save(usuarioResp);
        return usuarioResp.getId();
    }
}
