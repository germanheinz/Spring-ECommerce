package com.springbootangular.api.v1.mapper;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    public static final String NAME = "Jim";
    public static final long ID = 1L;

    ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Test
    void clienteToClienteDTO() {
        Cliente cliente = new Cliente();
        cliente.setId(ID);
        cliente.setNombre(NAME);

        ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);

        assertEquals(String.valueOf(NAME), clienteDTO.getNombre());
        assertEquals(NAME, clienteDTO.getNombre());
    }

    @Test
    void clienteDTOToCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(NAME);

        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);

        assertEquals(String.valueOf(NAME), cliente.getNombre());
        assertEquals(NAME, cliente.getNombre());
    }
}