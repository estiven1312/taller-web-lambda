package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sala")
@Data
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idPiso")
    private Piso piso;
    private Boolean haveTv;
    private Integer numberSeats;
    private String urlImage;


}
