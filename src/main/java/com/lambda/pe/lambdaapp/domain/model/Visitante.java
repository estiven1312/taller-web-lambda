package com.lambda.pe.lambdaapp.domain.model;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReservaVisita")
    private ReservaVisita reservaVisita;

}
