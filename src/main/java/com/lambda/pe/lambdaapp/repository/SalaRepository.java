package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Cubiculo;
import com.lambda.pe.lambdaapp.domain.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    @Query("SELECT c FROM Sala c WHERE c.id NOT IN (" +
            "SELECT r.sala.id FROM ReservaSala r WHERE " +
            "(" +
            "(r.horaInicio <= :dateInit AND r.horaFin > :dateInit) OR " +
            "(r.horaInicio < :dateEnd AND r.horaFin >= :dateEnd) OR " +
            "(r.horaInicio >= :dateInit AND r.horaFin <= :dateEnd) " +
            ")" +
            ")")
    List<Sala> findAvailableCubiculos(@Param("dateInit") Date dateInit, @Param("dateEnd") Date dateEnd);
}
