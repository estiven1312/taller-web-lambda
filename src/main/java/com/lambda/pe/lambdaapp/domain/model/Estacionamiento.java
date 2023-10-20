package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "estacionamiento")
@Data
public class Estacionamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPiso")
    private Piso piso;
}
