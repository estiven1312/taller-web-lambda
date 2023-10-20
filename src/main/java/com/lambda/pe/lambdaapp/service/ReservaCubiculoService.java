package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.model.Cubiculo;
import com.lambda.pe.lambdaapp.domain.model.ReservaCubiculos;

import java.util.Date;
import java.util.List;

public interface ReservaCubiculoService {
    List<ReservaCubiculos> getReservasByUser();

    List<Cubiculo> findEstacionamientosDisponibles(Date initDate, Date endDate);
}
