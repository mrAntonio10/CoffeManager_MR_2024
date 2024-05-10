package com.upb.coffe.db.model.orden.dto;

import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.orden.Orden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdenDto {
    Short id;
    String listaInventario;
    Float precio;

    public OrdenDto(Orden o) {
        this.id = o.getId();
        this.precio = o.getPrecio();
        this.listaInventario = this.getStringList(o.getListaInventario());
    }

    private String getStringList(List<Inventario> inventarioList) {
        StringBuilder sb = new StringBuilder();
        Iterator<Inventario> it = inventarioList.iterator();

        sb.setLength(0);
        while(it.hasNext()) {
            Inventario inv = it.next();
            if(sb.length() == 0) {
                sb.append(inv.getNombre());
            } else {
                sb.append(", "+inv.getNombre());
            }
        }
        return sb.toString();
    }


}
