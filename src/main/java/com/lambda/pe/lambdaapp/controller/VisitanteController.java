package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.AmbienteDTO;
import com.lambda.pe.lambdaapp.domain.dto.VisitaDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.domain.model.Visitante;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.service.AmbienteService;
import com.lambda.pe.lambdaapp.service.VisitaService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosEnum;
import com.lambda.pe.lambdaapp.util.Constants;
import com.lambda.pe.lambdaapp.util.TipoAmbienteEnum;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping(value = "/visitante")
public class VisitanteController {
    private final AmbienteService ambienteService;
    private final VisitaService visitaService;

    public VisitanteController(AmbienteService ambienteService, VisitaService visitaService) {
        this.ambienteService = ambienteService;
        this.visitaService = visitaService;
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("REPORT", "report.pdf");
        return ResponseEntity.ok().headers(headers).body(visitaService.getReporte());
    }
    @RequestMapping( method = RequestMethod.GET)
    public String getPageVisitanteGerente(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);

        List<Ambiente> estacionamientos = ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.ESTACIONAMIENTO.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
        model.addAttribute("estacionamientos", estacionamientos);
        List<Ambiente> salas = ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.SALA.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
        model.addAttribute("salas", salas);
        List<Visitante> visitantes = visitaService.visitasActivas(user);
        model.addAttribute("visitantes", visitantes);
        return "/gestion_visita";
    }
    @RequestMapping(value = "/seguridad", method = RequestMethod.GET)
    public String getPageVisitanteSeguridad(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);


        List<Visitante> visitantes = visitaService.getVisitantesSeguridadPorDia();
        model.addAttribute("visitantes", visitantes);
        return "/visitante_seguridad";
    }
    @RequestMapping(value = "/deleteVisitante/{id}", method = RequestMethod.GET)
    public String delete(
            @PathVariable(name="id") Long id,
            Model model,
            HttpSession httpSession) {
        System.out.println("======================ENTRANDO A Eliminar");
        Response response = visitaService.deleteVisita(id);
        if(!response.getHttpStatus().is2xxSuccessful()) {
            model.addAttribute("error", "Ocurrio un error, intentar nuevamente");
        }
        return "redirect:/visitante";

    }
    @RequestMapping(value = "/saveVisitante", method = RequestMethod.POST)
    public String save(
            @RequestParam(name="id", required=false) Long id,
            @RequestParam(name="nombres", required=false) String nombres,
            @RequestParam(name = "apellidos", required=false) String apellidos,
            @RequestParam(name = "dni", required=false) String dni,
            @RequestParam(name = "telefono", required=false, defaultValue = "false") String telefono,
            @RequestParam(name= "correo", required=false, defaultValue = "0") String correo,
            @RequestParam(name= "date", required=false, defaultValue = "false") String date,
            @RequestParam(name= "end", required=false, defaultValue = "false") String end,
            @RequestParam(name= "init",  required=false, defaultValue = "false") String init,
            @RequestParam(name= "idEstacionamiento", defaultValue = "") Long idEstacionamiento,
            @RequestParam(name= "idAmbiente", defaultValue = "") Long idAmbiente,

            Model model,
            HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        System.out.println("======================ENTRANDO A ATUALIZAR");
        VisitaDTO visitaDTO = new VisitaDTO(id, nombres, apellidos, dni, telefono, correo, date, end, init, idEstacionamiento, idAmbiente);
        Response response = visitaService.saveVisita(visitaDTO, user);
        return "redirect:/visitante";
    }
}
