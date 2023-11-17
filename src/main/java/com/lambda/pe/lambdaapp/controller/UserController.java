package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.UserService;
import com.lambda.pe.lambdaapp.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);

        return "/perfil";
    }
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam(name="imagen", required=false) MultipartFile imagen,
                             @RequestParam(name="id", required=false) Long id,
                             @RequestParam("nombres") String nombres,
                             @RequestParam("apellidos") String apellidos,
                             @RequestParam("telefono") String telefono,
                             @RequestParam("numeroDocumentoIdentificacion") String numeroDocumentoIdentificacion,
                             @RequestParam("correo") String correo,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setNombres(nombres);
        userDTO.setMultipartFile(imagen);
        userDTO.setApellidos(apellidos);
        userDTO.setTelefono(telefono);
        userDTO.setNumeroDocumentoIdentificacion(numeroDocumentoIdentificacion);
        userDTO.setCorreo(correo);
        userDTO.setPassword(password);
        userDTO.setUsername(username);
        userService.registerUser(userDTO, httpSession);
        System.gc();
        return "redirect:/user";
    }


}
