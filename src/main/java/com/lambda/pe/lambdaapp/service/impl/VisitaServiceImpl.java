package com.lambda.pe.lambdaapp.service.impl;

import com.lambda.pe.lambdaapp.domain.dto.VisitaDTO;
import com.lambda.pe.lambdaapp.domain.model.*;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.repository.AmbienteRepository;
import com.lambda.pe.lambdaapp.repository.CatalogoDetalleRepository;
import com.lambda.pe.lambdaapp.repository.ReservaRepository;
import com.lambda.pe.lambdaapp.repository.VisitaRepository;
import com.lambda.pe.lambdaapp.service.EmailService;
import com.lambda.pe.lambdaapp.service.VisitaService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.DateUtil;
import com.lambda.pe.lambdaapp.util.EstadoReservaEnum;
import com.lambda.pe.lambdaapp.util.JasperReportUtil;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class VisitaServiceImpl implements VisitaService {
    Logger LOGGER = LoggerFactory.getLogger(VisitaServiceImpl.class);
    private final VisitaRepository visitaRepository;
    private final CatalogoDetalleRepository catalogoDetalleRepository;
    private final JasperReportUtil jasperReportUtil;
    private final AmbienteRepository ambienteRepository;
    private final ReservaRepository reservaRepository;

    private final EmailService emailService;
    public VisitaServiceImpl(VisitaRepository visitaRepository, CatalogoDetalleRepository catalogoDetalleRepository, JasperReportUtil jasperReportUtil, AmbienteRepository ambienteRepository, ReservaRepository reservaService, EmailService emailService) {
        this.visitaRepository = visitaRepository;
        this.catalogoDetalleRepository = catalogoDetalleRepository;
        this.jasperReportUtil = jasperReportUtil;
        this.ambienteRepository = ambienteRepository;
        this.reservaRepository = reservaService;
        this.emailService = emailService;
    }
    @Override

    public List<Visitante> visitasActivas(User user){
        return visitaRepository.getVisitasByUser(user.getId());
    }

    @Override
    public byte[] getReporte() throws JRException, FileNotFoundException {
        List<VisitaDTO> visitaDTOS = new ArrayList<>();
        List<Visitante> visitantes = getVisitantesSeguridadPorDia();
        for(Visitante v: visitantes){
            VisitaDTO visitaDTO = new VisitaDTO();
            visitaDTO.setNombres(v.getNombres());
            visitaDTO.setApellidos(v.getApellidos());
            visitaDTO.setFechaVisita(v.getReservaVisita().getInit());
            visitaDTO.setDni(v.getDni());
            visitaDTO.setCorreo(v.getCorreo());
            visitaDTOS.add(visitaDTO);
        }
        return jasperReportUtil.exportToPdf(visitaDTOS);
    }
    @Override

    public Response deleteVisita(Long id){
        Response response = new Response();
        try{
            Visitante visitante = visitaRepository.getReferenceById(id);
            if(visitante.getReservaEstacionamiento() != null){
                reservaRepository.delete(visitante.getReservaEstacionamiento());
                visitante.setReservaEstacionamiento(null);
            }
            emailService.send(
                    "lambdaapp@gmail.com",
                    visitante.getCorreo(),
                    "SE HA CANCELADO SU VISITA",
                    "Su visita en las oficinas de LAMBDA SAC el día " +
                            DateUtil.convertDateToString(visitante.getReservaVisita().getInit(), DateUtil.FORMAT_DATE) +
                            "a las " + DateUtil.convertDateToString(visitante.getReservaVisita().getInit(), DateUtil.FORMAT_HOUR) + " hasta las "+
                            DateUtil.convertDateToString(visitante.getReservaVisita().getEnd(), DateUtil.FORMAT_HOUR) + " FUE CANCELADA");
            Reserva reservaVisita = visitante.getReservaVisita();
            visitaRepository.delete(visitante);
            reservaRepository.delete(reservaVisita);

            response.setId(id.toString());
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);

        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
            response.setId(id.toString());
            response.setMessage("FAIL");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    @Override
    public List<Visitante> getVisitantesSeguridadPorDia(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);
        cal2.set(Calendar.MILLISECOND, 999);
        return visitaRepository.getVisitasFromToday(cal.getTime(), cal2.getTime());
    }
    @Override
    @Transactional
    public Response saveVisita(VisitaDTO visitaDTO, User user){
        Response response = new Response();
        try{
            Reserva reserva = new Reserva();
            CatalogoDetalle catalogoDetalle = catalogoDetalleRepository.getByAbreviatura(CatalogoEnum.ESTADO_RESERVA.getLabel(), EstadoReservaEnum.VIGENTE.getLabel());
            Reserva reservaEstacionamiento = null;
            Date init = DateUtil.convertStringToDate(visitaDTO.getDate() + " " + visitaDTO.getInit(), DateUtil.FORMAT_DATE_HOUR_MIN);
            Date end = DateUtil.convertStringToDate(visitaDTO.getDate() + " " + visitaDTO.getEnd(), DateUtil.FORMAT_DATE_HOUR_MIN);
            if(visitaDTO.getIdEstacionamiento() != null){
                reservaEstacionamiento = new Reserva();
                reservaEstacionamiento.setResponsible(null);

                Ambiente estacionamiento = ambienteRepository.getReferenceById(visitaDTO.getIdEstacionamiento());
                reservaEstacionamiento.setAmbiente(estacionamiento);
                reservaEstacionamiento.setInit(init);
                reservaEstacionamiento.setEnd(end);
                reservaEstacionamiento.setEstado(catalogoDetalle);
                reservaRepository.save(reservaEstacionamiento);

            }
            Ambiente sala = null;

            if(visitaDTO.getIdAmbiente() != null){
                sala = ambienteRepository.getReferenceById(visitaDTO.getIdAmbiente());
            }
            reserva.setResponsible(user);
            reserva.setAmbiente(sala);
            reserva.setInit(init);
            reserva.setEnd(end);
            reserva.setEstado(catalogoDetalle);
            reservaRepository.save(reserva);
            Visitante visitante = new Visitante();
            if(visitaDTO.getId() != null) {
                visitante = visitaRepository.getReferenceById(visitaDTO.getId());
                if(visitante.getReservaEstacionamiento() != null){
                    reservaRepository.deleteById(visitante.getReservaEstacionamiento().getId());
                }
                reservaRepository.deleteById(visitante.getReservaVisita().getId());

            }
            visitante.setNombres(visitaDTO.getNombres());
            visitante.setDni(visitaDTO.getDni());
            visitante.setApellidos(visitaDTO.getApellidos());
            visitante.setCorreo(visitaDTO.getCorreo());
            visitante.setTelefono(visitaDTO.getTelefono());
            visitante.setReservaVisita(reserva);
            visitante.setReservaEstacionamiento(reservaEstacionamiento);
            visitaRepository.save(visitante);
            emailService.send(
                    "lambdaapp@gmail.com",
                    visitante.getCorreo(),
                    "HA SIDO INVITADO A UNA VISITA",
                    "Se le ha registrado para una visita en las oficinas de LAMBDA SAC el día " +
                            DateUtil.convertDateToString(visitante.getReservaVisita().getInit(), DateUtil.FORMAT_DATE) +
                    "a las " + DateUtil.convertDateToString(visitante.getReservaVisita().getInit(), DateUtil.FORMAT_HOUR) + " hasta las "+
                            DateUtil.convertDateToString(visitante.getReservaVisita().getEnd(), DateUtil.FORMAT_HOUR));
            response.setId(String.valueOf(visitante.getId()));
            response.setMessage("OK");
            response.setHttpStatus(HttpStatus.OK);
            return response;
        } catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
            response.setId(null);
            response.setMessage("FAIL");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
