package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reserva_sala")
@Data
public class ReservaSala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date horaInicio;
    private Date horaFin;
    private Integer cantidadPersonas;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idResponsible")
    private User responsible;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idSala")
    private Sala sala;

}
