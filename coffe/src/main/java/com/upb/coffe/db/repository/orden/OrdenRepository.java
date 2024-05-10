package com.upb.coffe.db.repository.orden;


import com.upb.coffe.db.model.orden.Orden;
import com.upb.coffe.db.model.orden.dto.OrdenDto;
import com.upb.coffe.db.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
