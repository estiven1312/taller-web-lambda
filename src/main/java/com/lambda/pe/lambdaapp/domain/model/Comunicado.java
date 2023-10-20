package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Comunicado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensajeComunicado;
    private String urlImagen;
    private Date fechaComunicado;

}
