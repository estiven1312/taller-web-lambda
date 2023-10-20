package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.ReservaCubiculos;
import com.lambda.pe.lambdaapp.domain.model.ReservaSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaCubiculoRepository extends JpaRepository<ReservaCubiculos, Long> {
    @Query("SELECT r FROM ReservaCubiculos r WHERE r.user.id = :idUser")
    List<ReservaCubiculos> findByUserId(@Param("idUser") Long idUser);
}
