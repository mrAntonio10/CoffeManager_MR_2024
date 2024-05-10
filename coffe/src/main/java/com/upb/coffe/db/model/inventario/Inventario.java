package com.upb.coffe.db.model.inventario;

import com.upb.coffe.db.model.usuario.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "INVENTARIO")
public class Inventario implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_INVENTARIO_ID_GENERATOR", sequenceName = "SEQ_INVENTARIO_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVENTARIO_ID_GENERATOR")
    @Column(name = "ID")
    private Short id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "STOCK")
    private int stock;

    @Column(name = "PRECIO")
    private Float precio;

    @Column(name = "ESTADO")
    private Boolean estado;
}


