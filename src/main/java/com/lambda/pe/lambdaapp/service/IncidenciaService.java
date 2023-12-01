package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.model.IncidenciaAmbiente;
import com.lambda.pe.lambdaapp.domain.model.User;

import java.util.List;

public interface IncidenciaService {
    void registerIncidencia(String descripcion, Long idAmbiente, User user);

    void changeStateIncidencia(Long idIncidencia);

    void deleteIncidencia(Long idIncidencia);

    List<IncidenciaAmbiente> getReporteIncidencias();
}
