package com.upb.coffe.db.service.impl;


import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.repository.inventario.InventarioRepository;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import com.upb.coffe.db.service.InventarioService;
import com.upb.coffe.db.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class InventarioServiceImpl implements InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioServiceImpl(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }
    @Override
    public List<Inventario> findAllByEstadoFalse() {
        return inventarioRepository.findAllByEstadoFalseOrderByIdAsc();
    }

    @Override
    public Long save(@RequestBody Inventario inventario) {
        Optional<Inventario> invOpt = this.inventarioRepository.findById(inventario.getId());

        if (invOpt.isPresent()) {
           Inventario inv = invOpt.get();
            inv.setNombre(inventario.getNombre());
            inv.setEstado(inventario.getEstado());
            inv.setPrecio(inventario.getPrecio());
            inv.setStock(inventario.getStock());
        this.inventarioRepository.save(inv);
        return  inv.getId();
        } else {
            log.info("Hola pai vas a crear nuevo usuario");
            Inventario invNew = new Inventario();
                invNew.setNombre(inventario.getNombre());
                invNew.setEstado(inventario.getEstado());
                invNew.setStock(inventario.getStock());
                invNew.setPrecio(inventario.getPrecio());
           invNew = this.inventarioRepository.save(inventario);
            return invNew.getId();
        }
    }

    @Override
    public Long deleteInventario(Long id) {
        Optional<Inventario> inventarioOpt = this.inventarioRepository.findById(id);
        if(!inventarioOpt.isPresent()) {
            throw new NoSuchElementException("inventario con id: " + id + "no encontrado");
        }
        inventarioOpt.get().setEstado(true);
        Inventario inventarioResp = inventarioOpt.get();
            inventarioResp.setEstado(true);
            inventarioResp = this.inventarioRepository.save(inventarioResp);
        return inventarioResp.getId();
    }
}
