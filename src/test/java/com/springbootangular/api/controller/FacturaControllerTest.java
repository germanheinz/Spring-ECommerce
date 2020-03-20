package com.springbootangular.api.controller;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.services.ClienteService;
import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FacturaControllerTest {

    @Mock
    ClienteService customerService;

    @InjectMocks
    FacturaController facturaController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(facturaController).build();
    }

    @Test
    void getFacturaById() throws Exception{
        Date date = new Date();
        date.setTime(date.getTime());

        Factura factura = new Factura();
        factura.setId(1l);
        factura.setCreateAt("12/12/2020");
        factura.setDescripcion("test");
        factura.setObservacion("test");

        List<Factura> listFacturas = new ArrayList<Factura>();
        listFacturas.add(factura);

        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNombre("test");
        cliente.setApellido("test");
        cliente.setCreateAt(date);
        cliente.setFacturas(listFacturas);

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setCliente(cliente);

        when(customerService.findFacturaById(1l)).thenReturn(facturaDTO);
        mockMvc.perform(get("/factura/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void filtrarProductos() {
    }

    @Test
    void delete() {
    }

    @Test
    void createFactura() {
    }
}