package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.ReservaSalaDTO;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;
import com.lambda.pe.lambdaapp.domain.model.ReservaSala;
import com.lambda.pe.lambdaapp.domain.model.Sala;
import com.lambda.pe.lambdaapp.domain.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReservaSalaService {
    List<Sala> salasDisponibles(Date init, Date end);
    List<ReservaSala> getReservasByUser(User user);

    ReservaSala findById(Long id);

    List<Sala> findAll();

    List<Sala> findSalaDisponibles(Date initDate, Date endDate);

    Map<String, String> reservar(ReservaSalaDTO reservaSalaDTO, User user);

    Map<String, String> delete(Long id);
}
