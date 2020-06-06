package com.springbootangular.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="facturas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String observacion;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @JsonIgnoreProperties(value={"facturas", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({"facturas", "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //Anotacion fundamental para general la llave foranea factura_id en la tabla factura_items
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;

    public Factura() {
        items = new ArrayList<>();
    }

    /*@PrePersist
    public void prePersist() {
        Date date = new Date();
        this.createAt = String.valueOf(date);
    }*/
    public Double getTotal() {
        Double total = 0.00;
        for (ItemFactura item : items) {
            total += item.getImporte();
        }
        return total;
    }
    private static final long serialVersionUID = 1L;
}
