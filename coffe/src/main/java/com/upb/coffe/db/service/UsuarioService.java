package com.upb.coffe.db.service;



import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;

import java.util.HashMap;
import java.util.List;

public interface UsuarioService {


    Long save(Usuario usuarioDto);

    HashMap<String, Object> findByUsernameAndPassword(String nombreUsuario, String password);

    List<UsuarioDto> findAllUsers();

    Long deleteUsuario(Long id);

}
