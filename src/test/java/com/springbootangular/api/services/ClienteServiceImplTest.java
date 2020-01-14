package com.springbootangular.api.services;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.repositories.ClienteRepository;
import com.springbootangular.api.repositories.FacturaRepository;
import com.springbootangular.api.repositories.ProductoRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.mapper.FacturaMapper;
import com.springbootangular.api.v1.mapper.UsuarioMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.v1.model.FacturaDTO;
import com.springbootangular.api.v1.model.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    ClienteService clienteService;

    @Mock
    FacturaRepository facturaRepository;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository, facturaRepository, productoRepository, ClienteMapper.INSTANCE, FacturaMapper.INSTANCE);
    }

    @Test
    void findAll() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("nombre");
        cliente.setApellido("apellido");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("nombre2");
        cliente2.setApellido("apellido2");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente, cliente2));
        //when
        List<ClienteDTO> clientesDTO = clienteService.findAll();
        //then
        assertEquals(2, clientesDTO.size());
    }

    @Test
    void FindAllPage() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("nombre");
        cliente.setApellido("apellido");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("nombre2");
        cliente2.setApellido("apellido2");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente, cliente2));
        //when
        List<ClienteDTO> clientesDTO = clienteService.findAll();
        //then
        assertEquals(2, clientesDTO.size());
    }

    @Test
    void findById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Michael");
        cliente.setApellido("apellido");

        when(clienteRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(cliente));
        //when
        ClienteDTO customerDTO = clienteService.findById(1L);
        assertEquals("Michael", customerDTO.getNombre());
    }

    @Test
    void save() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Michael");
        clienteDTO.setApellido("Apellido");

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        //when
        ClienteDTO savedDTO = clienteService.saveCustomerByDTO(1L, clienteDTO);
        //then
        assertEquals(clienteDTO.getNombre(), savedDTO.getNombre());
        assertEquals("Michael", savedDTO.getNombre());
    }

    @Test
    void saveCustomerByDTO() {
        //given
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Jim");

        Cliente savedCustomer = new Cliente();
        savedCustomer.setNombre(clienteDTO.getNombre());
        savedCustomer.setApellido(clienteDTO.getApellido());
        savedCustomer.setId(1l);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCustomer);

        //when
        ClienteDTO savedDto = clienteService.saveCustomerByDTO(1L, clienteDTO);

        //then
        assertEquals(clienteDTO.getNombre(), savedDto.getNombre());
        assertEquals("Jim", savedDto.getNombre());
    }

    @Test
    void findByIdCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Jim");
        clienteDTO.setApellido("Carrey");

        Cliente savedCustomer = new Cliente();
        savedCustomer.setNombre(clienteDTO.getNombre());
        savedCustomer.setApellido(clienteDTO.getApellido());
        savedCustomer.setId(1l);

        when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(savedCustomer));
        assertEquals("Jim", clienteDTO.getNombre());
    }

    @Test
    void deleteCustomerById() {
        Long id = 1L;
        clienteRepository.deleteById(id);
        verify(clienteRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findFacturaById() {
        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNombre("Jim");
        cliente.setApellido("Carrey");

        Factura factura = new Factura();
        factura.setId(1L);
        factura.setCliente(cliente);

        when(facturaRepository.findById(1L)).thenReturn(Optional.ofNullable(factura));
        assertEquals("Jim", cliente.getNombre());
    }

    @Test
    void saveFactura() {
        Cliente cliente = new Cliente();
        cliente.setId(1l);
        cliente.setNombre("Jim");
        cliente.setApellido("Carrey");

        Factura factura = new Factura();
        factura.setId(1L);
        factura.setCliente(cliente);

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setCliente(cliente);
        facturaDTO.setId(1L);

        facturaRepository.save(factura);
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        FacturaDTO facturaDTOSaved = clienteService.saveFacturaByDTO(1L, facturaDTO);

        //then
        assertEquals(facturaDTO.getId(), facturaDTOSaved.getId());
    }

    @Test
    void saveFacturaByDTO() {

    }

    @Test
    void deleteFacturaById() {
        Long id = 1L;
        facturaRepository.deleteById(id);
        verify(facturaRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByNombre() {
    }
}