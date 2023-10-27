package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.repository.UserRepository;
import com.lambda.pe.lambdaapp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    Logger LOG = LoggerFactory.getLogger(HomeController.class);
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute(Constants.USER_KEY_SESSION.label);
        User user1 = userRepository.findById(user.getId()).orElse(null);
        LOG.info(user1.getRol().getNombre());
        model.addAttribute("rol", user1.getRol());

        return "/home";
    }
}
