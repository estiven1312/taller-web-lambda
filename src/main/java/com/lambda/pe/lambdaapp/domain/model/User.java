package com.lambda.pe.lambdaapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String numeroDocumentoIdentificacion;
    private String correo;
    private String username;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolId")
    private Role rol;

    private String rutaFoto;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idTipoIdentificacionCatalogo", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idTipoIdentificacionCatalogoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle tipoDocIdentificacion;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idEstadoUsuarioCatalogo", referencedColumnName = "idCatalogo", insertable = true, updatable = true),
            @JoinColumn(name = "idEstadoUsuarioCatalogoDetalle", referencedColumnName = "idItem", insertable = true, updatable = true)})
    private CatalogoDetalle estadoUsuario;
}
