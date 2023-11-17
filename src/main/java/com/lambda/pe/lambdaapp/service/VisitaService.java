package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.VisitaDTO;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.domain.model.Visitante;
import com.lambda.pe.lambdaapp.domain.util.Response;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.List;

public interface VisitaService {
    List<Visitante> visitasActivas(User user);

    byte[] getReporte() throws JRException, FileNotFoundException;

    Response deleteVisita(Long id);

    List<Visitante> getVisitantesSeguridadPorDia();

    Response saveVisita(VisitaDTO visitaDTO, User user);
}
