package com.upb.coffe.db.repository.venta;


import com.upb.coffe.db.model.orden.Orden;
import com.upb.coffe.db.model.venta.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
