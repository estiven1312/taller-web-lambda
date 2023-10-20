package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva_cubiculos")
public @Data class ReservaCubiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCubiculo")
    private Cubiculo cubiculo;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser")
    private User user;
    private Date fechaInicio;
    private Date fechaFin;}
