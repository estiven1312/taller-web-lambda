package com.lambda.pe.lambdaapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "visitante")
public @Data class Visitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idReservaVisita")
    private Reserva reservaVisita;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idReservaEstacionamiento")
    private Reserva reservaEstacionamiento;

}
