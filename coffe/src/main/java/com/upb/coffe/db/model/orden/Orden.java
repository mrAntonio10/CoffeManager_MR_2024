package com.upb.coffe.db.model.orden;

import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EnableJpaAuditing
@AllArgsConstructor
@Builder
@Table(name = "ORDEN")
@EntityListeners(value = { AuditingEntityListener.class} )
public class Orden implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_ORDEN_ID_GENERATOR", sequenceName = "SEQ_ORDEN_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDEN_ID_GENERATOR")
    @Column(name = "ID")
    private Short id;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Column(name = "LISTA_ORDEN")
    private List<Inventario> listaInventario;

    @Column(name = "FECHA_ORDEN")
    @CreatedDate
    private Date fechaOrden;

    @Column(name = "PRECIO")
    private Float precio;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

}



