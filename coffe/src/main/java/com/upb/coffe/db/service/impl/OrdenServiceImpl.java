package com.upb.coffe.db.service.impl;


import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.orden.Orden;
import com.upb.coffe.db.model.orden.dto.OrdenDto;
import com.upb.coffe.db.repository.inventario.InventarioRepository;
import com.upb.coffe.db.repository.orden.OrdenRepository;
import com.upb.coffe.db.service.OrdenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.OperationsException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {
    private final OrdenRepository ordenRepository;
    private final InventarioRepository inventarioRepository;

    @Override
    @Transactional
    public OrdenDto newOrden(List<Short> listaInventarioId) throws OperationsException {
        HashMap<String, Object> tupla = this.getInventarioById(listaInventarioId);

        Orden orden = Orden.builder()
                .listaInventario((List<Inventario>) tupla.get("lista"))
                .precio((Float) tupla.get("precio"))
                .estado(false)
                .build();

        ordenRepository.save(orden);

        return new OrdenDto(orden);
    }

    private HashMap<String, Object> getInventarioById(List<Short> invIdList) throws OperationsException {
        Iterator<Short> it = invIdList.iterator();

        List<Inventario> invList = new ArrayList<>();
        HashMap resp = new HashMap();
        Float precioTotal = 0.0f;
        while(it.hasNext()) {
            Inventario inv = inventarioRepository.findById(it.next())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el producto de id " +it.next()));
            if( inv.getStock() > 0 ) {
                invList.add(inv);
                precioTotal = precioTotal + inv.getPrecio();

                inv.setStock( inv.getStock() - 1 );
                inventarioRepository.save(inv);
            } else {
                throw new OperationsException("Por el momento no contamos con la cantidad de " +1+ " para el producto " +inv.getNombre());
            }
        }
        resp.put("lista", invList);
        resp.put("precio", precioTotal);

        return resp;
    }
}
