package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.model.Estacionamiento;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;

import java.util.Date;
import java.util.List;

public interface ReservaEstacionamientoService {
    List<ReservaEstacionamiento> getReservasByUser();

    List<Estacionamiento> findEstacionamientosDisponibles(Date initDate, Date endDate);
}
