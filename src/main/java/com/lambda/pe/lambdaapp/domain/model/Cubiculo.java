package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cubiculo")
@Data
public class Cubiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean computadoraInd;
    private Boolean segundaPantallaInd;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPiso")
    private Piso piso;
}
