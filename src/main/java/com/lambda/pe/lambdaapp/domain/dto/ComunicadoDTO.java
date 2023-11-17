package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public @Data class ComunicadoDTO {
    private Long id;
    private String descripcion;
    private String imagenUrl;
    private MultipartFile multipartFile;
    private Date fechaComunicado;
}
