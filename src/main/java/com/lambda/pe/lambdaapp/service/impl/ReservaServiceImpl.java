package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.dto.ReservaDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.CatalogoDetalle;
import com.lambda.pe.lambdaapp.domain.model.Reserva;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.AmbienteRepository;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.repository.ReservaRepository;
import com.lambda.pe.lambdaapp.service.ReservaService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.DateUtil;
import com.lambda.pe.lambdaapp.util.EstadoReservaEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservaServiceImpl implements ReservaService {
    Logger logger = LoggerFactory.getLogger(ReservaServiceImpl.class);
    private final AmbienteRepository ambienteRepository;
    private final ReservaRepository reservaRepository;

    private final CatalogoDetalleRepository catalogoDetalleRepository;
    public ReservaServiceImpl(AmbienteRepository ambienteRepository, ReservaRepository reservaRepository, CatalogoDetalleRepository catalogoDetalleRepository) {
        this.ambienteRepository = ambienteRepository;
        this.reservaRepository = reservaRepository;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
    }

    @Override
    public List<Reserva> getReservasByUser(User user, String tipo) {
        return reservaRepository.findByUserId(tipo, user.getId());
    }
    @Override
    public Reserva findById(Long id){
        return reservaRepository.findById(id).orElse(null);
    }


    @Override
    public List<Ambiente> findAmbientesByTipoDisponibles(String tipo, Date initDate, Date endDate) {
        return ambienteRepository.findAvailableAmbientesByTipo(tipo, initDate, endDate);
    }

    @Override
    public Map<String, String> reservar(ReservaDTO reservaDTO, User user){
        HashMap<String, String> response = new HashMap<>();
        try{
            CatalogoDetalle catalogoDetalle = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_RESERVA.getLabel(), EstadoReservaEnum.VIGENTE.getLabel());
            Ambiente estacionamiento = ambienteRepository.getReferenceById(reservaDTO.getIdAmbiente());
            Date init = DateUtil.convertStringToDate(reservaDTO.getDate() + " " + reservaDTO.getInitHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            Date end = DateUtil.convertStringToDate(reservaDTO.getDate() + " " + reservaDTO.getEndHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            Reserva reservaEstacionamiento = new Reserva();
            if(reservaDTO.getIdReserva() != null){
                reservaEstacionamiento = reservaRepository.getReferenceById(reservaDTO.getIdReserva());
            }
            reservaEstacionamiento.setEstado(catalogoDetalle);
            reservaEstacionamiento.setResponsible(user);
            reservaEstacionamiento.setEnd(end);
            reservaEstacionamiento.setInit(init);
            reservaEstacionamiento.setAmbiente(estacionamiento);
            reservaRepository.save(reservaEstacionamiento);
            response.put("STATUS", "OK");
            return response;
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            response.put("STATUS", "FAIL");
            return response;
        }
    }

    @Override
    public Map<String, String> delete(Long id){
        HashMap<String, String> response = new HashMap<>();
        try{
            reservaRepository.deleteById(id);
            response.put("STATUS", "OK");
            return response;
        }catch (Exception ex){
            response.put("STATUS", "FAIL");
            return response;
        }
    }
}
