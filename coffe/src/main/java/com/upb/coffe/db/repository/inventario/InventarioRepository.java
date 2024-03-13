package com.upb.coffe.db.repository.inventario;


import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.orden.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
