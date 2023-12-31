package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ReservaDTO;
import com.lambda.pe.lambdaapp.domain.model.*;
import com.lambda.pe.lambdaapp.service.AmbienteService;
import com.lambda.pe.lambdaapp.service.ReservaService;
import com.lambda.pe.lambdaapp.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sala")
public class ReservaSalaController {
    private final ReservaService reservaSalaService;
    private final AmbienteService ambienteService;

    Logger LOG = LoggerFactory.getLogger(ReservaSalaController.class);

    public ReservaSalaController(ReservaService reservaSalaService, AmbienteService ambienteService) {
        this.reservaSalaService = reservaSalaService;
        this.ambienteService = ambienteService;
    }

    @RequestMapping(value = "/findSalasDisponibles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> findEstacionamientosDisponibles(@RequestParam("date") String date, @RequestParam("initHour") String initHour, @RequestParam("endHour") String endHour){
        LOG.info(date, initHour, endHour);
        Date init = DateUtil.convertStringToDate(date + " " + initHour, DateUtil.FORMAT_DATE_HOUR_MIN);
        Date end = DateUtil.convertStringToDate(date + " " + endHour, DateUtil.FORMAT_DATE_HOUR_MIN);

        return new ResponseEntity<>(reservaSalaService.findAmbientesByTipoDisponibles(TipoAmbienteEnum.SALA.getLabel(),init, end), HttpStatus.OK);
    }
    @RequestMapping(value = "/findSalasDisponiblesEdit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> findEstacionamientosDisponiblesEdit(@PathVariable("id") Long id){
        Reserva reserva = reservaSalaService.findById(id);
        List<Ambiente> ambientes = reservaSalaService.findAmbientesByTipoDisponibles(TipoAmbienteEnum.SALA.getLabel(),reserva.getInit(), reserva.getEnd());
        Ambiente ambiente =reserva.getAmbiente();
        ambientes.add(ambiente);
        return new ResponseEntity<>(ambientes, HttpStatus.OK);
    }
    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<Reserva> reservaSalas = reservaSalaService.getReservasByUser(user, TipoAmbienteEnum.SALA.getLabel());
        model.addAttribute("reservas",reservaSalas);
        return "/salas";
    }

    @RequestMapping(value = "/getReservaSala/{idReserva}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> getReservaEstacionamiento(@PathVariable Long idReserva, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        Reserva reservaSala = reservaSalaService.findById(idReserva);
        return new ResponseEntity<>(reservaSala, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSalas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> getEstacionamientos(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.SALA.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel()), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @RequestBody ReservaDTO reservaDTO){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaSalaService.reservar(reservaDTO, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteReserva/{idReserva}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @PathVariable Long idReserva){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaSalaService.delete(idReserva), HttpStatus.OK);
    }
}
