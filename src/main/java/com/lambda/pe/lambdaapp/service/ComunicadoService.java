package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.dto.ComunicadoDTO;
import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import com.lambda.pe.lambdaapp.domain.util.Response;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public interface ComunicadoService {
    void saveComunicado(String mensajeComunicado, String urlImagen, Date fechaComunicado);

    Response saveComunicado(ComunicadoDTO comunicadoDTO, HttpSession httpSession);

    List<Comunicado> listAllComunicados();

    Response deleteComunicado(Long id);

    ResponseEntity<Comunicado> getById(Long id);
}
