package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "piso")
public class Piso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Boolean aptoPublico;

}
