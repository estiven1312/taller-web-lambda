package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.ReservaEstacionamientoDTO;
import com.lambda.pe.lambdaapp.domain.model.Estacionamiento;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;
import com.lambda.pe.lambdaapp.domain.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReservaEstacionamientoService {
    List<ReservaEstacionamiento> getReservasByUser(User user);

    ReservaEstacionamiento findById(Long id);

    List<Estacionamiento> findAll();

    List<Estacionamiento> findEstacionamientosDisponibles(Date initDate, Date endDate);

    Map<String, String> reservar(ReservaEstacionamientoDTO reservaEstacionamientoDTO, User user);

    Map<String, String> delete(Long id);
}
