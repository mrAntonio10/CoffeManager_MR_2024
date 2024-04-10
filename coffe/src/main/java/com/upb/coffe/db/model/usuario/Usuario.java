package com.upb.coffe.db.model.usuario;

import com.upb.coffe.db.model.orden.Orden;
import com.upb.coffe.db.model.usuario.enums.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;


import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USUARIORUSH")
public class Usuario implements Serializable {
    @Id
    @SequenceGenerator(name = "SEQ_USUARIO_ID_GENERATOR", sequenceName = "SEQ_USUARIO_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO_ID_GENERATOR")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "KONG_CONSUMER")
    private String consumer;

    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    private Roles rol;

    @Column(name = "ESTADO")
    private Boolean estado;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Column(name = "USUARIO_ORDEN")
    private List<Orden> orden;



}
