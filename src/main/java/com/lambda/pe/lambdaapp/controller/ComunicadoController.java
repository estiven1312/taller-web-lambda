package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.ComunicadoDTO;
import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
import com.lambda.pe.lambdaapp.domain.model.Comunicado;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.domain.util.Response;
import com.lambda.pe.lambdaapp.service.ComunicadoService;
import com.lambda.pe.lambdaapp.util.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/comunicado")
public class ComunicadoController {
    private final ComunicadoService comunicadoService;

    public ComunicadoController(ComunicadoService comunicadoService) {
        this.comunicadoService = comunicadoService;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        List<Comunicado> comunicadoList = comunicadoService.listAllComunicados();
        model.addAttribute("comunicados", comunicadoList);

        return "/comunicado_admin";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@RequestParam(name="imagen", required=false) MultipartFile imagen,
                             @RequestParam(name="id", required=false) Long id,
                             @RequestParam(name = "descripcion", required = false) String descripcion,
                             HttpSession httpSession){
        ComunicadoDTO comunicadoDTO = new ComunicadoDTO();
        comunicadoDTO.setId(id);
        comunicadoDTO.setDescripcion(descripcion);
        comunicadoDTO.setMultipartFile(imagen);
        comunicadoService.saveComunicado(comunicadoDTO, httpSession);
        System.gc();
        return "redirect:/comunicado";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response delete(@PathVariable("id") Long id,
                           HttpSession httpSession){

        return  comunicadoService.deleteComunicado(id);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comunicado> getComunicadoById(@PathVariable("id") Long id,
                                            HttpSession httpSession){
        return comunicadoService.getById(id);
    }
}
