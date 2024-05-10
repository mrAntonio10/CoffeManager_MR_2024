package com.upb.coffe.db.service;


import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.orden.dto.OrdenDto;

import javax.management.OperationsException;
import java.util.List;

public interface OrdenService {
    OrdenDto newOrden(List<Short> listaInventario) throws OperationsException;
}
