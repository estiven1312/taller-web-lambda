package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.responsible.id = :idUser AND r.ambiente.tipoAmbiente.abreviatura = :tipo")
    List<Reserva> findByUserId(@Param("tipo") String tipo, @Param("idUser") Long idUser);
    @Query("SELECT r FROM Reserva r WHERE r.ambiente.tipoAmbiente.abreviatura = :tipoAmbiente")
    List<Reserva> findAllByAmbienteTipoAmbiente(@Param("tipoAmbiente")String tipoAmbiente);
}
