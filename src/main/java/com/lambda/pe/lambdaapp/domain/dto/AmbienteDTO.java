package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public @Data class AmbienteDTO {
    private Long id;
    private String nombreAmbiente;
    private Integer numeroPiso;
    private Boolean indicadorTelevisor;
    private Integer cantidadAsientos;
    private MultipartFile imagen;
    private Boolean indicadorProyector;
    private Boolean computadoraIndicador;
    private String tipoAmbiente;
    private String estado;

    public AmbienteDTO(Long id, String nombreAmbiente, Integer numeroPiso, Boolean indicadorTelevisor, Integer cantidadAsientos, MultipartFile imagen, Boolean indicadorProyector, Boolean computadoraIndicador, String tipoAmbiente, String estado) {
        this.id = id;
        this.nombreAmbiente = nombreAmbiente;
        this.numeroPiso = numeroPiso;
        this.indicadorTelevisor = indicadorTelevisor;
        this.cantidadAsientos = cantidadAsientos;
        this.imagen = imagen;
        this.indicadorProyector = indicadorProyector;
        this.computadoraIndicador = computadoraIndicador;
        this.tipoAmbiente = tipoAmbiente;
        this.estado = estado;
    }
}
