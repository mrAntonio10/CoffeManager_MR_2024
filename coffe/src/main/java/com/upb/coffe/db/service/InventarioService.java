package com.upb.coffe.db.service;



import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;

import java.util.List;

public interface InventarioService {
    List<Inventario> findAllByEstadoFalse();

    Long save(Inventario inventario);

    Long deleteInventario(Long id);
}
