package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.ReservaSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaSalaRepository extends JpaRepository<ReservaSala, Long> {
    @Query("SELECT r FROM ReservaSala r WHERE r.responsible.id = :idUser")
    List<ReservaSala> findByUserId(@Param("idUser") Long idUser);
}
