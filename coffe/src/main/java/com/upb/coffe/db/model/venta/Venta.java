package com.upb.coffe.db.model.venta;

import com.upb.coffe.db.model.orden.Orden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "VENTA")
public class Venta implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_VENTA_ID_GENERATOR", sequenceName = "SEQ_VENTA_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VENTA_ID_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "ID_ORDEN")
    private Orden orden;

    @Column(name = "FECHA_VENTA")
    private Date fechaVenta;

}



