package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.CatalogoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogoDetalleRepository extends JpaRepository<CatalogoDetalle, Long> {
    @Query("SELECT c FROM CatalogoDetalle c WHERE c.abreviatura = :catalogoDetalle AND c.catalogo.abreviatura = :catalogo")
    CatalogoDetalle getByAbreviatura(@Param("catalogo") String catalogo,
                                            @Param("catalogoDetalle") String catalogoDetalle);
}
