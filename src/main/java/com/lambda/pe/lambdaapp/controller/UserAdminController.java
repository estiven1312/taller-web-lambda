package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.dto.UserDTO;
import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.UserService;
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
@RequestMapping(value = "/user-admin")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(Model model, HttpSession httpSession){
        List<User> users = userService.findActivos();
        model.addAttribute("usuarios", users);
        return "/admin-user";
    }
    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@RequestParam(name="imagen", required=false) MultipartFile imagen,
                             @RequestParam(name="id", required=false) Long id,
                             @RequestParam(name="rol", required=false) Long rol,
                             @RequestParam(name="tipoIdentificacion", required=false) String tipoIdentificacion,
                             @RequestParam("nombres") String nombres,
                             @RequestParam("apellidos") String apellidos,
                             @RequestParam("telefono") String telefono,
                             @RequestParam("numeroDocumentoIdentificacion") String numeroDocumentoIdentificacion,
                             @RequestParam("correo") String correo,
                             @RequestParam("username") String username,
                             @RequestParam(value = "password", required = false) String password,
                             HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setNombres(nombres);
        userDTO.setMultipartFile(imagen);
        userDTO.setRol(rol);
        userDTO.setTipoIdentificacion(tipoIdentificacion);
        userDTO.setApellidos(apellidos);
        userDTO.setTelefono(telefono);
        userDTO.setNumeroDocumentoIdentificacion(numeroDocumentoIdentificacion);
        userDTO.setCorreo(correo);
        userDTO.setPassword(password);
        userDTO.setUsername(username);
        userService.registerUser(userDTO, httpSession);
        System.gc();
        return "redirect:/user-admin";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id,
                         HttpSession httpSession){
        userService.deleteUser(id);
        return "redirect:/user-admin";
    }
}
