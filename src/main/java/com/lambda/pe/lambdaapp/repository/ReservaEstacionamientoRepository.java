package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.ReservaCubiculos;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ReservaEstacionamientoRepository extends JpaRepository<ReservaEstacionamiento, Long> {
    @Query("SELECT r FROM ReservaEstacionamiento r WHERE r.user.id = :idUser")
    List<ReservaEstacionamiento> findByUserId(@Param("idUser") Long idUser);
}
