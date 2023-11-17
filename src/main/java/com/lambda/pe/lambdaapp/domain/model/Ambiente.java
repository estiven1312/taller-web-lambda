package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Ambiente")
public @Data class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAmbiente;
    private Integer numeroPiso;
    private Boolean indicadorTelevisor;
    private Integer cantidadAsientos;
    private String urlImagen;
    private Boolean indicadorProyector;
    private Boolean computadoraIndicador;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idTipoCatalogo", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idTipoCatalogoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle tipoAmbiente;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCatalogoEstado", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idCatalogoEstadoId", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle estado;
}
