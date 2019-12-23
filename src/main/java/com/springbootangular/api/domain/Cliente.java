package com.springbootangular.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.*;
import javax.swing.plaf.synth.Region;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Anotacion de lombock, simplifica clase, sin tener que visualizar setters y getters
@Data
//Anotacion para marcar que la clase va a ser una Entidad en la tabla
@Entity
//Anotacion usada en caso de que la tabla se quiera llamar diferente al de la clase
@Table(name="clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @NotEmpty(message = "EL campo no puede estar vacio")
    private String apellido;

    @NotEmpty(message = "EL campo no puede estar vacio")
    @Email(message = "Direccion de correo electronico erronea")
    @Column(nullable = false, unique = true)
    private String email;
    //La anotacion column va para las diferentes columnas en la tabla
    //pero especificamente para especificar un nombre diferente en que va a mappear en la tabla

    @NotNull(message = "El campo no puede estar vacio")
    @Column(name="create_at")
    //Anotacion para transformar el valor en un tipo DATE en la tabla
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    @JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }
}
