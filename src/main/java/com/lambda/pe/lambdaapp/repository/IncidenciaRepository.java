package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.IncidenciaAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<IncidenciaAmbiente, Long> {
    @Query("SELECT i FROM IncidenciaAmbiente i WHERE i.estado.abreviatura NOT IN ('RESUELTO', 'ELIMINADO')")
    List<IncidenciaAmbiente> findIncidenciasActivas();
}
