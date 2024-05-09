package com.lambda.pe.lambdaapp.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "catalogoDetalle")
public class CatalogoDetalle implements Serializable{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private Id id;

    @ManyToOne
    @JoinColumn(name = "idCatalogo", insertable = false, updatable = false)
    private Catalogo catalogo;

    private String descripcionLarga;
    private String descripcionCorta;
    private String abreviatura;
    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1L;
        private Long idCatalogo;
        private String idItem;

    }
}

