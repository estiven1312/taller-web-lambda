package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.AmbienteDTO;
import com.lambda.pe.lambdaapp.domain.model.Ambiente;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.service.AmbienteService;
import com.lambda.pe.lambdaapp.util.CatalogoEnum;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosEnum;
import com.lambda.pe.lambdaapp.util.TipoAmbienteEnum;
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
@RequestMapping(value = "/ambiente")
public class AmbienteController {
    private final AmbienteService ambienteService;


    public AmbienteController(AmbienteService ambienteService) {
        this.ambienteService = ambienteService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){

        return "/admin_ambiente";
    }
    @RequestMapping(value = "/estacionamiento", method = RequestMethod.GET)
    public String getPageEstacionamiento(Model model, HttpSession httpSession){
        List<Ambiente> estacionamientos = ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.ESTACIONAMIENTO.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
        model.addAttribute("estacionamientos", estacionamientos);
        return "/admin_estacionamiento";
    }

    @RequestMapping(value = "/sala", method = RequestMethod.GET)
    public String getPageSala(Model model, HttpSession httpSession){
        List<Ambiente> salas = ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.SALA.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
        model.addAttribute("salas", salas);
        return "/admin_sala";
    }
    @RequestMapping(value = "/cubiculo", method = RequestMethod.GET)
    public String getPageCubiculo(Model model, HttpSession httpSession){
        List<Ambiente> cubiculos = ambienteService.findAll(CatalogoEnum.TIPO_AMBIENTE.getLabel(), TipoAmbienteEnum.CUBICULO.getLabel(), CatalogoEnum.ESTADO_AUDITORIA.getLabel(), CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel());
        model.addAttribute("cubiculos", cubiculos);
        return "/admin_cubiculo";
    }
    @RequestMapping(value = "/saveAmbiente", method = RequestMethod.POST)
    public String save(
            @RequestParam(name="id", required=false) Long id,
            @RequestParam(name="imagen", required=false) MultipartFile imagen,
            @RequestParam(name = "nombreAmbiente", required=false) String nombreAmbiente,
            @RequestParam(name = "numeroPiso", required=false) Integer numeroPiso,
            @RequestParam(name = "indicadorTelevisor", required=false, defaultValue = "false") Boolean indicadorTelevisor,
            @RequestParam(name= "cantidadAsientos", required=false, defaultValue = "0") Integer cantidadAsientos,
            @RequestParam(name= "indicadorProyector", required=false, defaultValue = "false") Boolean indicadorProyector,
            @RequestParam(name= "computadoraIndicador", required=false, defaultValue = "false") Boolean computadoraIndicador,
            @RequestParam(name= "tipoAmbiente") String tipoAmbiente,
            @RequestParam(name= "estado", defaultValue = "ACTIVO") String estado,
            Model model,
            HttpSession httpSession) {
        System.out.println("======================ENTRANDO A ATUALIZAR");
        AmbienteDTO ambienteDTO = new AmbienteDTO(id, nombreAmbiente, numeroPiso, indicadorTelevisor, cantidadAsientos, imagen, indicadorProyector, computadoraIndicador, tipoAmbiente, estado);
        Response response = ambienteService.saveAmbiente(ambienteDTO);
        if(!response.getHttpStatus().is2xxSuccessful()) {
            model.addAttribute("error", "Ocurrio un error, intentar nuevamente");
        }
        if(tipoAmbiente.equals(TipoAmbienteEnum.ESTACIONAMIENTO.getLabel())){
            return "redirect:/ambiente/estacionamiento";
        } else if(tipoAmbiente.equals(TipoAmbienteEnum.CUBICULO.getLabel())){
            return "redirect:/ambiente/cubiculo";
        } else if(tipoAmbiente.equals(TipoAmbienteEnum.SALA.getLabel())){
            return "redirect:/ambiente/sala";
        } else {
            return "redirect:/ambiente";
        }
    }
    @RequestMapping(value = "/deleteAmbiente/{id}", method = RequestMethod.GET)
    public String delete(
            @PathVariable(name="id") Long id,
            @RequestParam(name = "tipoAmbiente")  String tipoAmbiente,
            Model model,
            HttpSession httpSession) {
        System.out.println("======================ENTRANDO A Eliminar");
        Response response = ambienteService.deleteAmbiente(id);
        if(!response.getHttpStatus().is2xxSuccessful()) {
            model.addAttribute("error", "Ocurrio un error, intentar nuevamente");
        }
        if(tipoAmbiente.equals(TipoAmbienteEnum.ESTACIONAMIENTO.getLabel())){
            return "redirect:/ambiente/estacionamiento";
        } else if(tipoAmbiente.equals(TipoAmbienteEnum.CUBICULO.getLabel())){
            return "redirect:/ambiente/cubiculo";
        } else if(tipoAmbiente.equals(TipoAmbienteEnum.SALA.getLabel())){
            return "redirect:/ambiente/sala";
        } else {
            return "redirect:/ambiente";
        }
    }

}
