package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Catalogo")
public @Data class Catalogo {
    @Id
    private Long id;
    private String abreviatura;
    private String descripcion;
}
