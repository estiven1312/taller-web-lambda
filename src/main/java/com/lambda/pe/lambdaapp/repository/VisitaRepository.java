package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Visitante;
import com.lambda.pe.lambdaapp.service.VisitaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visitante, Long> {
    @Query("SELECT v FROM Visitante v WHERE v.reservaVisita.responsible.id = :idUser AND v.reservaVisita.estado.abreviatura = 'VIGENTE'")
    List<Visitante> getVisitasByUser(@Param("idUser") Long idUser);
    @Query("SELECT v FROM Visitante v WHERE v.reservaVisita.init >= :init AND v.reservaVisita.end <= :end")
    List<Visitante> getVisitasFromToday(@Param("init") Date init, @Param("end") Date end);
}
