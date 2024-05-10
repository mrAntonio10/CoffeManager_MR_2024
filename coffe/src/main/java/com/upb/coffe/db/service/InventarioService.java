package com.upb.coffe.db.service;



import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.inventario.dto.InventarioDtoForSelect;
import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.model.usuario.dto.UsuarioDto;

import java.util.List;

public interface InventarioService {
    List<Inventario> findAllByEstadoFalse();

    List<InventarioDtoForSelect> findAllDtoByEstadoFalse();


    Short save(Inventario inventario);

    Short deleteInventario(Short id);
}
