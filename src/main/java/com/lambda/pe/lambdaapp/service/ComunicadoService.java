package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.model.Comunicado;

import java.util.Date;
import java.util.List;

public interface ComunicadoService {
    void saveComunicado(String mensajeComunicado, String urlImagen, Date fechaComunicado);
    List<Comunicado> listAllComunicados();
}
