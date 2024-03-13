package com.upb.coffe.db.model.orden;

import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDEN")
public class Orden implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_ORDEN_ID_GENERATOR", sequenceName = "SEQ_ORDEN_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDEN_ID_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Column(name = "LISTA_ORDEN")
    private List<Inventario> listaInventario;

    @Column(name = "FECHA_ORDEN")
    private Date fechaOrden;

    @Column(name = "PRECIO")
    private Float precio;

    @Column(name = "ESTADO")
    private Boolean estado;
}



