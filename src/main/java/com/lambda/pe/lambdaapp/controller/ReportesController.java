package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.ReporteService;
import com.lambda.pe.lambdaapp.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReportesController {

    private final ReporteService reporteService;
    @RequestMapping(value = "/reportes", method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return "reportes";
    }

    @RequestMapping(value = "/reportes", method = RequestMethod.POST)
    public String actualizar(@RequestParam(name="imagen", required=false) MultipartFile imagen,
                             @RequestParam(name="comentario", required=false) String comentario,
                             @RequestParam(name="referencia", required=false) String referencia,
                             HttpSession httpSession,Model model){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        try{
            reporteService.registerReport(imagen, referencia, comentario, user);
            model.addAttribute("okMessage", "Se registró correctamente");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            model.addAttribute("errorMessage", "No se pudo registrar el reporte, no se detectó basura o no es un formato .jpeg, jpg o png");

        }
        System.gc();
        return "/reportes";
    }
}
