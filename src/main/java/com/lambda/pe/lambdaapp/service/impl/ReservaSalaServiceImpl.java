package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.dto.ReservaSalaDTO;
import com.lambda.pe.lambdaapp.domain.model.*;
import com.lambda.pe.lambdaapp.repository.ReservaSalaRepository;
import com.lambda.pe.lambdaapp.repository.SalaRepository;
import com.lambda.pe.lambdaapp.service.ReservaSalaService;
import com.lambda.pe.lambdaapp.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservaSalaServiceImpl implements ReservaSalaService {
    private final ReservaSalaRepository reservaSalaRepository;
    private final SalaRepository salaRepository;

    public ReservaSalaServiceImpl(ReservaSalaRepository reservaSalaRepository, SalaRepository salaRepository) {
        this.reservaSalaRepository = reservaSalaRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public List<Sala> salasDisponibles(Date init, Date end) {
        return salaRepository.findAvailableCubiculos(init, end);
    }

    @Override
    public List<ReservaSala> getReservasByUser(User user) {
        return reservaSalaRepository.findByUserId(user.getId());
    }
    @Override
    public ReservaSala findById(Long id){
        return reservaSalaRepository.findById(id).orElse(null);
    }
    @Override
    public List<Sala> findAll(){
        return salaRepository.findAll();
    }
    @Override
    public List<Sala> findSalaDisponibles(Date initDate, Date endDate) {
        return salaRepository.findAvailableCubiculos(initDate, endDate);
    }

    @Override
    public Map<String, String> reservar(ReservaSalaDTO reservaSalaDTO, User user){
        HashMap<String, String> response = new HashMap<>();
        try{
            Sala sala = salaRepository.getReferenceById(reservaSalaDTO.getIdSala());
            Date init = DateUtil.convertStringToDate(reservaSalaDTO.getDate() + " " + reservaSalaDTO.getInitHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            Date end = DateUtil.convertStringToDate(reservaSalaDTO.getDate() + " " + reservaSalaDTO.getEndHour(), DateUtil.FORMAT_DATE_HOUR_MIN);
            ReservaSala reservaSala = new ReservaSala();
            reservaSala.setResponsible(user);
            reservaSala.setHoraFin(end);
            reservaSala.setHoraInicio(init);
            reservaSala.setSala(sala);
            reservaSalaRepository.save(reservaSala);
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
            reservaSalaRepository.deleteById(id);
            response.put("STATUS", "OK");
            return response;
        }catch (Exception ex){
            response.put("STATUS", "FAIL");
            return response;
        }
    }
}
