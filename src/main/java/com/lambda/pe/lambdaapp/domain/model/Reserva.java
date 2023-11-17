package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Reserva")
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date init;
    private Date end;
    @ManyToOne
    @JoinColumn(name = "idResponsable")
    private User responsible;
    @ManyToOne
    @JoinColumn(name = "idAmbiente")
    private Ambiente ambiente;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCatalogoEstado", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idCatalogoEstadoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle estado;

}
