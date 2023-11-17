package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comunicado")
@Data
public class Comunicado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String urlImagen;
    private Date fechaComunicado;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCatalogoEstado", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idCatalogoEstadoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle estado;

}
