package com.upb.coffe.rest.inventario;

import com.upb.coffe.db.model.inventario.Inventario;
import com.upb.coffe.db.model.inventario.dto.InventarioDtoForSelect;
import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.service.InventarioService;
import com.upb.coffe.db.service.UsuarioService;
import com.upb.coffe.db.service.VentaService;
import com.upb.coffe.rest.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8084"}, allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class InventarioController {

    private final InventarioService inventarioService;



    @GetMapping("/find-all")
    public ResponseEntity<?> inventarioFindAll() {
        try{
            log .info("Solicitud lista de inventario");
            return ok(inventarioService.findAllByEstadoFalse());
        }
        catch (Exception e){
            log.info("Error inesperado {}", e);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Error al listar inventario");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @GetMapping("/select")
    public ResponseEntity<?> inventarioFindForSelect() {
        try{
            log .info("Solicitud lista de inventario");
            return ok(inventarioService.findAllDtoByEstadoFalse());
        }
        catch (Exception e){
            log.info("Error inesperado {}", e);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Error al listar inventario");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @PostMapping()
    public ResponseEntity inventarioCreate(@RequestBody Inventario inventario){
        try{
            log.info("Servicio crear Inventario: {}", inventario);
            return ok(this.inventarioService.save(inventario));
        } catch(Exception e){
            log.info("Error inesperado {}", e);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Error al crear inventario");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @PutMapping("/delete-inventory")
    ResponseEntity<?> deleteInventario(@RequestBody Short id) {
        try {
            log.info("Eliminar inventario con id: {}", id);
            return ok(inventarioService.deleteInventario(id));
        } catch (NoSuchElementException e) {
            log.info("Usuario no encontrado, message {}", e.getMessage());
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("mensaje", "Usuario con id " + id + " no encontrado");
            responseBody.put("status", HttpStatus.NOT_FOUND.value() + " " + HttpStatus.NOT_FOUND.getReasonPhrase());

            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e) {
            log.info("Error inesperado {}", e);
            return ResponseEntity.badRequest().body("Error inesperado");
        }
    }
}
