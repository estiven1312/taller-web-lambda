package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
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
@RequestMapping(value = "/reportes")
@Slf4j
public class ReportesController {

    private final ReporteService reporteService;
    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        return "/report_garbage";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String actualizar(@RequestParam(name="imagen", required=false) MultipartFile imagen,
                             @RequestParam(name="comentario", required=false) String comentario,
                             @RequestParam(name="referencia", required=false) String referencia,
                             HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        try{
            reporteService.registerReport(imagen, referencia, comentario, user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        System.gc();
        return "redirect:/reportes";
    }
}
