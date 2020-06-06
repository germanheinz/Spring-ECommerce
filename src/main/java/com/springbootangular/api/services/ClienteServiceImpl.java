package com.springbootangular.api.services;


import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.domain.Producto;
import com.springbootangular.api.repositories.ClienteRepository;
import com.springbootangular.api.repositories.FacturaRepository;
import com.springbootangular.api.repositories.ProductoRepository;
import com.springbootangular.api.v1.mapper.ClienteMapper;
import com.springbootangular.api.v1.mapper.FacturaMapper;
import com.springbootangular.api.v1.model.ClienteDTO;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    public ClienteRepository clienteRepository;

    public FacturaRepository facturaRepository;

    public ProductoRepository productoRepository;

    public ClienteMapper clienteMapper;

    public FacturaMapper facturaMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, FacturaRepository facturaRepository, ProductoRepository productoRepository, ClienteMapper clienteMapper, FacturaMapper facturaMapper) {
        this.clienteRepository = clienteRepository;
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.clienteMapper = clienteMapper;
        this.facturaMapper = facturaMapper;
    }

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
        FacturaDTO facturaDTO = facturaMapper.facturaToFacturaDTO(savedFactura);
        return facturaDTO;
    }

    @Override
    public FacturaDTO saveFacturaByDTO(Long id, FacturaDTO facturaDTO) {
        Factura factura = facturaMapper.facturaDTOToFactura(facturaDTO);
        factura.setId(id);
        return saveFacturaAndReturnDTO(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Producto> findByNombre(String term) {
        return productoRepository.findByNombre(term);
    }
}
