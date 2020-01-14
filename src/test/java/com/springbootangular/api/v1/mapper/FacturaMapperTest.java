package com.springbootangular.api.v1.mapper;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacturaMapperTest {

    public static final String NAME = "Jim";
    public static final long ID = 1L;

    FacturaMapper facturaMapper = FacturaMapper.INSTANCE;


    @Test
    void facturaToFacturaDTO() {
        Cliente cliente = new Cliente();
        cliente.setId(ID);
        cliente.setNombre("german");

        Factura factura = new Factura();
        factura.setId(ID);
        factura.setCliente(cliente);

        FacturaDTO facturaDTO = facturaMapper.facturaToFacturaDTO(factura);

        assertEquals(Long.valueOf(ID), facturaDTO.getId());
        assertEquals(cliente.getNombre(), facturaDTO.getCliente().getNombre());
    }

    @Test
    void facturaDTOToFactura() {
        Cliente cliente = new Cliente();
        cliente.setId(ID);
        cliente.setNombre("german");

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setId(ID);
        facturaDTO.setCliente(cliente);

        Factura factura = facturaMapper.facturaDTOToFactura(facturaDTO);

        assertEquals(Long.valueOf(ID), factura.getId());
        assertEquals(facturaDTO.getCliente().getNombre(), factura.getCliente().getNombre());
    }
}