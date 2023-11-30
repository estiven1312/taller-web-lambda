package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.ComunicadoRepository;
import com.lambda.pe.lambdaapp.repository.UserRepository;
import com.lambda.pe.lambdaapp.util.CatalogoEstadosEnum;
import com.lambda.pe.lambdaapp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    Logger LOG = LoggerFactory.getLogger(HomeController.class);
    private final UserRepository userRepository;
    private final ComunicadoRepository comunicadoRepository;

    public HomeController(UserRepository userRepository, ComunicadoRepository comunicadoRepository) {
        this.userRepository = userRepository;
        this.comunicadoRepository = comunicadoRepository;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        User user1 = userRepository.findById(user.getId()).orElse(null);
        Pageable pageable = PageRequest.of(0, 5);
        LOG.info(user1.getRol().getNombre());
        model.addAttribute("rol", user1.getRol());
        model.addAttribute("comunicadosSlider", comunicadoRepository.findSliderComunicados(CatalogoEstadosEnum.ESTADO_ACTIVO.getLabel(),pageable ));

        return "/home";
    }
}
