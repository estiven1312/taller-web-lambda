package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public @Data class UserDTO {
    private Long id;
    private String imagenUrl;
    private MultipartFile multipartFile;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String numeroDocumentoIdentificacion;
    private String correo;
    private String username;
    private String password;
}
