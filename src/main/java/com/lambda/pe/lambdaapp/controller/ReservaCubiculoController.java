package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ReservaDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.Reserva;
import com.lambda.pe.lambdaapp.domain.model.User;
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
@RequestMapping(value = "/cubiculo")
public class ReservaCubiculoController {
    private final ReservaService reservaService;
    private final AmbienteService ambienteService;

    Logger LOG = LoggerFactory.getLogger(ReservaCubiculoController.class);
    public ReservaCubiculoController(ReservaService reservaService, AmbienteService ambienteService) {
        this.reservaService = reservaService;
        this.ambienteService = ambienteService;
    }
    @RequestMapping(value = "/findCubiculosDisponibles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> findCubiculosDisponibles(@RequestParam("date") String date, @RequestParam("initHour") String initHour, @RequestParam("endHour") String endHour){
        LOG.info(date, initHour, endHour);
        Date init = DateUtil.convertStringToDate(date + " " + initHour, DateUtil.FORMAT_DATE_HOUR_MIN);
        Date end = DateUtil.convertStringToDate(date + " " + endHour, DateUtil.FORMAT_DATE_HOUR_MIN);

        return new ResponseEntity<>(reservaService.findAmbientesByTipoDisponibles(TipoAmbienteEnum.CUBICULO.getLabel(), init, end), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<Reserva> cubiculos = reservaService.getReservasByUser(user, TipoAmbienteEnum.CUBICULO.getLabel());
        model.addAttribute("reservas",cubiculos);
        return "/cubiculo";
    }

    @RequestMapping(value = "/getReservaCubiculo/{idReserva}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reserva> getReservaCubiculo(@PathVariable Long idReserva, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        Reserva reservaCubiculo = reservaService.findById(idReserva);
        return new ResponseEntity<>(reservaCubiculo, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCubiculos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> getCubiculos(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.CUBICULO.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel()), HttpStatus.OK);
    }
    @RequestMapping(value = "/findCubiculosDisponiblesEdit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ambiente>> findCubiculosDisponiblesEdit(@PathVariable("id") Long id){
        Reserva reserva = reservaService.findById(id);
        List<Ambiente> ambientes = reservaService.findAmbientesByTipoDisponibles(TipoAmbienteEnum.CUBICULO.getLabel(),reserva.getInit(), reserva.getEnd());
        Ambiente ambiente =reserva.getAmbiente();
        ambientes.add(ambiente);
        return new ResponseEntity<>(ambientes, HttpStatus.OK);
    }
    @RequestMapping(value = "/reservar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @RequestBody ReservaDTO reservaDTO){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaService.reservar(reservaDTO, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteReserva/{idReserva}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @PathVariable Long idReserva){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaService.delete(idReserva), HttpStatus.OK);
    }
}
