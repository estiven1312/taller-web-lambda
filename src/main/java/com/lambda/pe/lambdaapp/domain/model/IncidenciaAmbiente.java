package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Incidente_Ambiente")
public class IncidenciaAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idAmbiente")
    private Ambiente ambiente;
    @ManyToOne
    @JoinColumn(name = "idUserEmit")
    private User usuarioEmisor;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCatalogoEstado", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idCatalogoEstadoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle estado;
}
