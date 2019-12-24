package com.springbootangular.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private String nombre;
    private String apellido;
    private String email;
    private Date createAt;
}