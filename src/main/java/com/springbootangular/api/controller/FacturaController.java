package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.services.ClienteService;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http:localhost:4200"})
@RequestMapping(ClienteController.BASE_URL)
public class FacturaController {

    private ClienteService clienteService;

    public FacturaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/factura/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FacturaDTO getFacturaById(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }

    @GetMapping("/factura/products/find/{term}")
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteService.findByNombre(term);
    }

    @DeleteMapping("factura/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){clienteService.deleteFacturaById(id);
    }
    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaDTO createFactura(@RequestBody FacturaDTO facturaDTO){
        return clienteService.saveFactura(facturaDTO);
    }
}
