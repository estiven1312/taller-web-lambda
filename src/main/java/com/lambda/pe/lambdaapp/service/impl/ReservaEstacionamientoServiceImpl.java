package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.dto.ReservaEstacionamientoDTO;
import com.lambda.pe.lambdaapp.domain.model.Estacionamiento;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.EstacionamientoRepository;
import com.lambda.pe.lambdaapp.repository.ReservaEstacionamientoRepository;
import com.lambda.pe.lambdaapp.service.ReservaEstacionamientoService;
import com.lambda.pe.lambdaapp.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservaEstacionamientoServiceImpl implements ReservaEstacionamientoService {
    private final EstacionamientoRepository estacionamientoRepository;
    private final ReservaEstacionamientoRepository reservaEstacionamientoRepository;

    public ReservaEstacionamientoServiceImpl(EstacionamientoRepository estacionamientoRepository, ReservaEstacionamientoRepository reservaEstacionamientoRepository) {
        this.estacionamientoRepository = estacionamientoRepository;
        this.reservaEstacionamientoRepository = reservaEstacionamientoRepository;
    }

    @Override
    public List<ReservaEstacionamiento> getReservasByUser(User user) {
        return reservaEstacionamientoRepository.findByUserId(user.getId());
    }
    @Override
    public ReservaEstacionamiento findById(Long id){
        return reservaEstacionamientoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Estacionamiento> findAll(){
        return estacionamientoRepository.findAll();
    }
    @Override
    public List<Estacionamiento> findEstacionamientosDisponibles(Date initDate, Date endDate) {
        return estacionamientoRepository.findAvailableCubiculos(initDate, endDate);
    }

    @Override
    public Map<String, String> reservar(ReservaEstacionamientoDTO reservaEstacionamientoDTO, User user){
        HashMap<String, String> response = new HashMap<>();
        try{
            Estacionamiento estacionamiento = estacionamientoRepository.getReferenceById(reservaEstacionamientoDTO.getIdEstacionamiento());
            Date init = DateUtil.convertStringToDate(reservaEstacionamientoDTO.getDate() + " " + reservaEstacionamientoDTO.getInitHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            Date end = DateUtil.convertStringToDate(reservaEstacionamientoDTO.getDate() + " " + reservaEstacionamientoDTO.getEndHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            ReservaEstacionamiento reservaEstacionamiento = new ReservaEstacionamiento();
            reservaEstacionamiento.setUser(user);
            reservaEstacionamiento.setFechaFin(end);
            reservaEstacionamiento.setFechaInicio(init);
            reservaEstacionamiento.setEstacionamiento(estacionamiento);
            reservaEstacionamientoRepository.save(reservaEstacionamiento);
            response.put("STATUS", "OK");
            return response;
        }catch (Exception ex){
            response.put("STATUS", "FAIL");
            return response;
        }
    }

    @Override
    public Map<String, String> delete(Long id){
        HashMap<String, String> response = new HashMap<>();
        try{
            reservaEstacionamientoRepository.deleteById(id);
            response.put("STATUS", "OK");
            return response;
        }catch (Exception ex){
            response.put("STATUS", "FAIL");
            return response;
        }
    }
}
