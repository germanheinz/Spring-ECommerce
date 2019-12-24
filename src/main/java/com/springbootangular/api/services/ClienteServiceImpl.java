package com.springbootangular.api.services;


import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.repositories.ClienteRepository;
import com.springbootangular.api.repositories.FacturaRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.mapper.FacturaMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public FacturaRepository facturaRepository;

    @Autowired
    public ClienteMapper clienteMapper;

    @Autowired
    public FacturaMapper facturaMapper;

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::clienteToClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClienteDTO> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::clienteToClienteDTO);
    }

    @Override
    public ClienteDTO findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        return saveAndReturnDTO(clienteMapper.clienteDTOToCliente(clienteDTO));
    }

    @Override
    public ClienteDTO saveCustomerByDTO(Long id, ClienteDTO customerDTO) {
        Cliente customer = clienteMapper.clienteDTOToCliente(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    private ClienteDTO saveAndReturnDTO(Cliente cliente) {
        Cliente savedCustomer = clienteRepository.save(cliente);
        ClienteDTO returnDto = clienteMapper.clienteToClienteDTO(savedCustomer);
        return returnDto;
    }

    @Override
    public ClienteDTO findByIdCliente(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::clienteToClienteDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public FacturaDTO findFacturaById(Long id) {
        return facturaRepository.findById(id).map(facturaMapper::facturaToFacturaDTO).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public FacturaDTO saveFactura(FacturaDTO facturaDTO) {
        return saveFacturaAndReturnDTO(facturaMapper.facturaDTOToFactura(facturaDTO));
    }

    private FacturaDTO saveFacturaAndReturnDTO(Factura factura) {
        Factura savedFactura = facturaRepository.save(factura);
        FacturaDTO returnDto = facturaMapper.facturaToFacturaDTO(savedFactura);
        return returnDto;
    }

    @Override
    public FacturaDTO saveFacturaByDTO(Long id, FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.facturaDTOToFactura(facturaDTO);
        factura.setId(id);
        return saveFacturaAndReturnDTO(factura);
    }



    @Override
    public void deleteFacturaById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByNombre(String term) {
        return null;
    }
}
