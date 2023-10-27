package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ReservaEstacionamientoDTO;
import com.lambda.pe.lambdaapp.domain.model.Estacionamiento;
import com.lambda.pe.lambdaapp.domain.model.ReservaEstacionamiento;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.ReservaEstacionamientoService;
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
@RequestMapping(value = "/estacionamiento")
public class EstacionamientoController {
    public final ReservaEstacionamientoService reservaEstacionamientoService;

    Logger LOG = LoggerFactory.getLogger(EstacionamientoController.class);
    public EstacionamientoController(ReservaEstacionamientoService reservaEstacionamientoService) {
        this.reservaEstacionamientoService = reservaEstacionamientoService;
    }
    @RequestMapping(value = "/findEstacionamientosDisponibles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Estacionamiento>> findEstacionamientosDisponibles(@RequestParam("date") String date, @RequestParam("initHour") String initHour, @RequestParam("endHour") String endHour){
        LOG.info(date, initHour, endHour);
        Date init = DateUtil.convertStringToDate(date + " " + initHour, DateUtil.FORMAT_DATE_HOUR_MIN);
        Date end = DateUtil.convertStringToDate(date + " " + endHour, DateUtil.FORMAT_DATE_HOUR_MIN);

        return new ResponseEntity<>(reservaEstacionamientoService.findEstacionamientosDisponibles(init, end), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<ReservaEstacionamiento> reservaEstacionamientos = reservaEstacionamientoService.getReservasByUser(user);
        model.addAttribute("reservas",reservaEstacionamientos);
        return "/estacionamiento";
    }

    @RequestMapping(value = "/getReservaEstacionamiento/{idReserva}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaEstacionamiento> getReservaEstacionamiento(@PathVariable Long idReserva, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        ReservaEstacionamiento reservaEstacionamiento = reservaEstacionamientoService.findById(idReserva);
        return new ResponseEntity<>(reservaEstacionamiento, HttpStatus.OK);
    }

    @RequestMapping(value = "/getEstacionamientos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Estacionamiento>> getEstacionamientos(HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaEstacionamientoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/reservar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @RequestBody ReservaEstacionamientoDTO reservaEstacionamientoDTO){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaEstacionamientoService.reservar(reservaEstacionamientoDTO, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteReserva/{idReserva}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> reservar(HttpSession httpSession, @PathVariable Long idReserva){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return new ResponseEntity<>(reservaEstacionamientoService.delete(idReserva), HttpStatus.OK);
    }
}
