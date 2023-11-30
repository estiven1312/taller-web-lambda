package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {
    @Query("SELECT c FROM Comunicado c WHERE c.estado.abreviatura = :estado")
    public List<Comunicado> findAllComunicadosActivos(@Param("estado") String estado);

    @Query("SELECT c FROM Comunicado c WHERE c.estado.abreviatura = :estado ORDER BY c.fechaComunicado DESC")
    public List<Comunicado> findSliderComunicados(@Param("estado") String estado, Pageable pageable);
}
