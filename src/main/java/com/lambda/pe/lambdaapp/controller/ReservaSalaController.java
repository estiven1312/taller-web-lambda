package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ReservaEstacionamientoDTO;
import com.lambda.pe.lambdaapp.domain.dto.ReservaSalaDTO;
import com.lambda.pe.lambdaapp.domain.model.*;
import com.lambda.pe.lambdaapp.service.ReservaSalaService;
import com.lambda.pe.lambdaapp.util.Constants;
import com.lambda.pe.lambdaapp.util.DateUtil;
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
    public final ReservaSalaService reservaSalaService;
    Logger LOG = LoggerFactory.getLogger(ReservaSalaController.class);

    public ReservaSalaController(ReservaSalaService reservaSalaService) {
        this.reservaSalaService = reservaSalaService;
    }

    @RequestMapping(value = "/findSalasDisponibles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> findEstacionamientosDisponibles(@RequestParam("date") String date, @RequestParam("initHour") String initHour, @RequestParam("endHour") String endHour){
        LOG.info(date, initHour, endHour);
        Date init = DateUtil.convertStringToDate(date + " " + initHour, DateUtil.FORMAT_DATE_HOUR_MIN);
        Date end = DateUtil.convertStringToDate(date + " " + endHour, DateUtil.FORMAT_DATE_HOUR_MIN);

        return new ResponseEntity<>(reservaSalaService.findSalaDisponibles(init, end), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<ReservaSala> reservaSalas = reservaSalaService.getReservasByUser(user);
        model.addAttribute("reservas",reservaSalas);
        return "/salas";
    }

    @RequestMapping(value = "/getReservaSala/{idReserva}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaSala> getReservaEstacionamiento(@PathVariable Long idReserva, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        ReservaSala reservaSala = reservaSalaService.findById(idReserva);
        return new ResponseEntity<>(reservaSala, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSalas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sala>> getEstacionamientos(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaSalaService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @RequestBody ReservaSalaDTO reservaSalaDTO){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaSalaService.reservar(reservaSalaDTO, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteReserva/{idReserva}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @PathVariable Long idReserva){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaSalaService.delete(idReserva), HttpStatus.OK);
    }
}
