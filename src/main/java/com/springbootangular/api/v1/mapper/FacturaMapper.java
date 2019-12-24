package com.springbootangular.api.v1.mapper;

import com.springbootangular.api.domain.Factura;
import com.springbootangular.api.v1.model.FacturaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface FacturaMapper {

    FacturaMapper INSTANCE = Mappers.getMapper(FacturaMapper.class);

    FacturaDTO facturaToFacturaDTO(Factura factura);

    Factura facturaDTOToFactura(FacturaDTO facturaDTO);
}
