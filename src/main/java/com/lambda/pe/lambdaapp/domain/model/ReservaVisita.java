package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva_visita")
public @Data class ReservaVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPiso")
    private Piso piso;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idUser")
    private User user;
    private Date fechaInicio;
    private Date fechaFin;
}
