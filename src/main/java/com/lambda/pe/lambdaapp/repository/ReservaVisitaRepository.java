package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.ReservaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVisitaRepository extends JpaRepository<ReservaVisita, Long> {


}
