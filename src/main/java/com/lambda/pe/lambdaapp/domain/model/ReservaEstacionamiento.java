package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva_estacionamiento")
public @Data class ReservaEstacionamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEstacionamiento")
    private Estacionamiento estacionamiento;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser")
    private User user;
    private Date fechaInicio;
    private Date fechaFin;
}
