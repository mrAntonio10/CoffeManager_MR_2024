package com.upb.coffe.rest.orden;

import com.upb.coffe.db.service.OrdenService;
import com.upb.coffe.rest.request.CreateOrdenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8084"}, allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class OrdenController {
    private final OrdenService ordenService;

    @PostMapping("") ResponseEntity<?> createOrden(@RequestBody CreateOrdenRequest orden) {
        try {
            return ok(ordenService.newOrden(orden.getListaInventario()));
        } catch (NoSuchElementException e) {
            log.info("Elemento no encontrado: {}", e);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Error, elemento no encontrado " +e);
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e) {
            log.info("Error inesperado {}", e);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Error al crear orden " +e);
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
