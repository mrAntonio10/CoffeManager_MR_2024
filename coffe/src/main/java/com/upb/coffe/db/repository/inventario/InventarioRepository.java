package com.upb.coffe.db.repository.inventario;


import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.inventario.dto.InventarioDtoForSelect;
import com.upb.coffe.db.model.orden.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    @Query(" Select i FROM Inventario i " +
            " WHERE i.id = :id " +
            " AND i.estado = FALSE ")
    Optional<Inventario> findById(@Param("id") Short id);


    List<Inventario> findAllByEstadoFalseOrderByIdAsc();

    @Query("SELECT i " +
            "FROM Inventario i " +
            "WHERE i.estado = false " +
                "AND i.stock > 0 " +
                "ORDER BY i.id ASC "
    )
    List<InventarioDtoForSelect> findAllDtoByEstadoFalseOrderByIdAsc();

}
