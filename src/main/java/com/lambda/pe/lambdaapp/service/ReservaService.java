package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.ReservaDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.Reserva;
import com.lambda.pe.lambdaapp.domain.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReservaService {
    List<Reserva> getReservasByUser(User user, String tipo);

    Reserva findById(Long id);


    List<Ambiente> findAmbientesByTipoDisponibles(String tipoReserva, Date initDate, Date endDate);

    Map<String, String> reservar(ReservaDTO reservaDTO, User user);

    Map<String, String> delete(Long id);
}
