package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Cubiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface CubiculosRepository extends JpaRepository<Cubiculo, Long> {
    @Query("SELECT c FROM Cubiculo c WHERE c.id NOT IN (" +
            "SELECT r.cubiculo.id FROM ReservaCubiculos r WHERE " +
            "(" +
                "(r.fechaInicio <= :dateInit AND r.fechaFin > :dateInit) OR " +
                "(r.fechaInicio < :dateEnd AND r.fechaFin >= :dateEnd) OR " +
                "(r.fechaInicio >= :dateInit AND r.fechaFin <= :dateEnd) " +
            ")" +
            ")")
    List<Cubiculo> findAvailableCubiculos(@Param("dateInit") Date dateInit, @Param("dateEnd") Date dateEnd);
}
