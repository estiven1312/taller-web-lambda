package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "rol")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nombre;
    private Boolean permisoReservarSala;
    private Boolean permisoReservarEstacionamiento;
    private Boolean permisoReservarCubiculo;
}
