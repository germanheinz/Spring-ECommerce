package com.springbootangular.api.v1.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    private  String email;

    protected boolean canEqual(final Object other) {
        return other instanceof UsuarioDTO;
    }

}
