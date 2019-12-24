package com.springbootangular.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.domain.ItemFactura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {

    private Long id;
    private String descripcion;
    private String observacion;
    private Date createAt;
    private Cliente cliente;
    private List<ItemFactura> items;
}
