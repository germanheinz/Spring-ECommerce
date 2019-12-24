package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.services.ClienteService;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http:localhost:4200"})
@RestController
@RequestMapping(ClienteController.BASE_URL)
public class FacturaController {

    private ClienteService clienteService;

    public FacturaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/factura/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacturaDTO show(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }

    @DeleteMapping("factura/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){clienteService.deleteFacturaById(id);
    }
     @GetMapping("/factura/filter-productos/{term}")
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteService.findByNombre(term);
     }

     @PostMapping("/facturas")
     @ResponseStatus(HttpStatus.CREATED)
     public FacturaDTO crear(@RequestBody FacturaDTO facturaDTO){
        return clienteService.saveFactura(facturaDTO);
     }
}
