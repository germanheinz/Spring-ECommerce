package com.springbootangular.api.services;

import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClienteService {

    List<ClienteDTO> findAll();

    Page<ClienteDTO> findAll(Pageable pageable);

    ClienteDTO findById(Long id);

    ClienteDTO save(ClienteDTO clienteDTO);

    ClienteDTO saveCustomerByDTO(Long id, ClienteDTO customerDTO);

    void deleteCustomerById(Long id);

    ClienteDTO findByIdCliente(Long id);

    // Metodos Factura
    public FacturaDTO findFacturaById(Long id);

    public FacturaDTO saveFactura(FacturaDTO facturaDTO);

    FacturaDTO saveFacturaByDTO(Long id, FacturaDTO facturaDTO);

    public void deleteFacturaById(Long id);

    public List<Producto> findByNombre(String term);

}
