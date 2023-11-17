package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.AmbienteDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.util.Response;

import java.util.List;

public interface AmbienteService {
    Response saveAmbiente(AmbienteDTO ambienteDTO);

    List<Ambiente> findAll(String catalogo, String tipo, String catalogoEstado, String estado);

    Response deleteAmbiente(Long id);

    Ambiente getById(Long id);
}
