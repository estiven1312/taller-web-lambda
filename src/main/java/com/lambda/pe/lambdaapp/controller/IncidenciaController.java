package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ComunicadoDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import com.lambda.pe.lambdaapp.domain.model.IncidenciaAmbiente;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.AmbienteService;
import com.lambda.pe.lambdaapp.service.IncidenciaService;
import com.lambda.pe.lambdaapp.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/incidencia")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;

    private final AmbienteService ambienteService;

    public IncidenciaController(IncidenciaService incidenciaService, AmbienteService ambienteService) {
        this.incidenciaService = incidenciaService;
        this.ambienteService = ambienteService;
    }

    @RequestMapping( method = RequestMethod.GET, value = "/register-page")
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<Ambiente> ambientes = ambienteService.findAmbientesActivos();
        model.addAttribute("ambientes", ambientes);
        return "/register-incidence";
    }
    @RequestMapping( method = RequestMethod.GET, value = "/admin-page")
    public String getAdminPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<IncidenciaAmbiente> incidenciaAmbientes = incidenciaService.getReporteIncidencias();
        model.addAttribute("incidencias", incidenciaAmbientes);
        return "/admin-incidence";
    }

    @RequestMapping( method = RequestMethod.GET, value = "/change-state/{id}")
    public String changeState(@PathVariable("id") Long id,  Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        incidenciaService.changeStateIncidencia(id);
        return "redirect:/incidencia/admin-page";
    }

    @RequestMapping( method = RequestMethod.GET, value = "/delete/{id}")
    public String deleteIncidence(@PathVariable("id") Long id,  Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        incidenciaService.deleteIncidencia(id);
        return "redirect:/incidencia/admin-page";
    }
    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@RequestParam(name="descripcion") String descripcion,
                            @RequestParam(name="idAmbiente") Long idAmbiente,
                            HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        incidenciaService.registerIncidencia(descripcion, idAmbiente, user);
        return "redirect:/home";
    }
}
