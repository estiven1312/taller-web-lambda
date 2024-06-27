package com.lambda.pe.lambdaapp.service;

import com.lambda.pe.lambdaapp.domain.model.Reporte;
import com.lambda.pe.lambdaapp.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReporteService {
    void registerReport(MultipartFile multipartFile, String referencia, String comentario, User user) throws IOException;

    List<Reporte> listReports();
}
