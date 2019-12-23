package com.springbootangular.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 120)
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;

    //Cascada es para cuando se crea u elimina una de las dos entidades, tambien elimine la otra
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    //Con join table indicamos o personalizamos el nombre de la tabla intermedia
    //y debo indicarle cuales son los foreign key de cada tabla con joincolumn
    @JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="role_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
    private List<Role> roles;

    public static final long serialVersionUID = 1L;
}
