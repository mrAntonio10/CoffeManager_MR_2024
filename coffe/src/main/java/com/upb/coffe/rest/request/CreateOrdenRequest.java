package com.upb.coffe.rest.request;

import com.upb.coffe.db.model.inventario.Inventario;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrdenRequest {
    private List<Short> listaInventario;
}
