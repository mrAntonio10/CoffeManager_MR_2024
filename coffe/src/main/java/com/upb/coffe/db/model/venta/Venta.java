package com.upb.coffe.db.model.venta;

import com.upb.coffe.db.model.orden.Orden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "VENTA")
@EntityListeners(value = { AuditingEntityListener.class} )
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
    @CreatedDate
    private Date fechaVenta;

}



