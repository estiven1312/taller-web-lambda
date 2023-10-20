package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {
}
