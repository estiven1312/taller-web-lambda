package com.lambda.pe.lambdaapp.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VisitaDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private String date;
    private String end;
    private Date fechaVisita;
    private String init;
    private Long idEstacionamiento;
    private Long idAmbiente;

    public VisitaDTO() {
    }

    public VisitaDTO(Long id, String nombres, String apellidos, String dni, String telefono, String correo, String date, String end, String init, Long idEstacionamiento, Long idAmbiente) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.date = date;
        this.end = end;
        this.init = init;
        this.idEstacionamiento = idEstacionamiento;
        this.idAmbiente = idAmbiente;
    }

    public VisitaDTO(Long id, String nombres, String apellidos, String dni, String telefono, String correo, Long idEstacionamiento, Long idAmbiente) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.idEstacionamiento = idEstacionamiento;
        this.idAmbiente = idAmbiente;
    }
}
