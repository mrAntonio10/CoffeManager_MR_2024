package com.upb.coffe.db.model.inventario.dto;

import com.upb.coffe.db.model.inventario.Inventario;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventarioDtoForSelect {
    Short id;
    String nombre;

    public InventarioDtoForSelect(Inventario inv) {
        this.id = inv.getId();
        this.nombre = inv.getNombre();
    }
}
