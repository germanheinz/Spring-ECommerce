package com.springbootangular.api.controller;

import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.repositories.ClienteRepository;
import com.springbootangular.api.services.ClienteService;
import com.springbootangular.api.v1.model.ClienteDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClienteControllerTest {

    @Mock
    ClienteService customerService;

    @InjectMocks
    ClienteController customerController;

    @Mock
    ClienteRepository customerRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getListCustomers() throws Exception{
        ClienteDTO customerDTO = new ClienteDTO();
        customerDTO.setId(1l);
        customerDTO.setNombre("test");
        customerDTO.setApellido("test");

        ClienteDTO customerDTO2 = new ClienteDTO();
        customerDTO.setId(2l);
        customerDTO.setNombre("test");
        customerDTO.setApellido("test");

        List<ClienteDTO> customerDTO3 = Arrays.asList(customerDTO, customerDTO2);

        when(customerService.findAll()).thenReturn(customerDTO3);

        mockMvc.perform(get("/api/clientes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("test")))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetListCustomers() throws Exception{
        ClienteDTO customerDTO = new ClienteDTO();
        customerDTO.setId(1l);
        customerDTO.setNombre("test");
        customerDTO.setApellido("test");
        customerDTO.setEmail("test@test.com");

        int pageNumber = 1;
        int pageSize = 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<ClienteDTO> clienteDTOPage = new PageImpl<>(Collections.singletonList(customerDTO));

        when(customerService.findAll(pageable)).thenReturn(clienteDTOPage);
        Page<ClienteDTO> travellers = customerService.findAll(pageable);
        assertEquals(travellers.getNumberOfElements(), 1);
    }

    @Test
    void getCustomerById() throws Exception {
        Date date = new Date();
        date.setTime(date.getTime());

        ClienteDTO customerDTO = new ClienteDTO();
        customerDTO.setId(1l);
        customerDTO.setNombre("test");
        customerDTO.setApellido("test");
        customerDTO.setEmail("test@test.com");
        customerDTO.setCreateAt(date);

        when(customerService.findById(1l)).thenReturn(customerDTO);
        mockMvc.perform(get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomer() throws Exception{
        ClienteDTO customerDTO = new ClienteDTO();
        customerDTO.setNombre("test");
        customerDTO.setApellido("test");
        customerDTO.setId(1L);

        ClienteDTO returnDTO = new ClienteDTO();
        returnDTO.setNombre("test");
        returnDTO.setApellido("test");

        when(customerService.save(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/clientes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestController.asJsonString(customerDTO)))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.firstName", equalTo("test")))
    }

    @Test
    void updateCustomer() throws Exception{
        ClienteDTO customer = new ClienteDTO();
        customer.setId(1L);
        customer.setNombre("Fred");
        customer.setApellido("Flintstone");

        ClienteDTO returnDTO = new ClienteDTO();
        returnDTO.setNombre(customer.getNombre());
        returnDTO.setApellido(customer.getApellido());
        returnDTO.setId(customer.getId());

        when(customerService.saveCustomerByDTO(anyLong(), any(ClienteDTO.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put( "/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestController.asJsonString(returnDTO))) //ver si ira DTO
                .andExpect(status().isNotFound());
                /*.andExpect(jsonPath("$.firstname", Matchers.equalTo("Fred")))
                .andExpect(jsonPath("$.lastname", Matchers.equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", Matchers.equalTo(CustomerController.BASE_URL + "/1")));*/


    }
}