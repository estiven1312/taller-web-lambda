package com.lambda.pe.lambdaapp.repository;

import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
    @Query("SELECT c FROM Ambiente c WHERE c.id NOT IN (" +
            "SELECT r.ambiente.id FROM Reserva r WHERE " +
            "(" +
                "(r.init <= :dateInit AND r.end > :dateInit) OR " +
                "(r.init < :dateEnd AND r.end >= :dateEnd) OR " +
                "(r.init >= :dateInit AND r.end <= :dateEnd) " +
            ")" +
            ") AND c.tipoAmbiente.abreviatura = :tipo AND c.estado.abreviatura = 'ACTIVO'")
    List<Ambiente> findAvailableAmbientesByTipo(@Param("tipo") String tipo,@Param("dateInit") Date dateInit, @Param("dateEnd") Date dateEnd);

    @Query("SELECT c " +
            "FROM Ambiente c " +
            "WHERE c.tipoAmbiente.abreviatura = :tipo " +
            "AND c.tipoAmbiente.catalogo.abreviatura = :abreviaturaCatalogo " +
            "AND c.estado.abreviatura = :estadoAmbiente " +
            "AND c.estado.catalogo.abreviatura = :estadoCatalogo")
    List<Ambiente> findAllAmbientesByTipo(@Param("abreviaturaCatalogo") String abreviaturaTipoCatalogo,
                                          @Param("tipo") String tipo,
                                          @Param("estadoCatalogo") String abreviaturaEstadoCatalogo,
                                          @Param("estadoAmbiente") String estadoAmbiente);

    @Query("SELECT a FROM Ambiente a WHERE a.estado.abreviatura <> 'ELIMINADO'")
    List<Ambiente> findAmbientesActivos();
}
