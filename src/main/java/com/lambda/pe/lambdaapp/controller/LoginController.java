package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.domain.model.User;
import com.lambda.pe.lambdaapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession httpSession,
                        Model model){

        if (userService.validateUser(username, password, httpSession)) {
            return "redirect:/home";
        }
        model.addAttribute("errorMessage", "Invalid Credentials");
        model.addAttribute("username", username);
        return "/login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(Model model, HttpSession httpSession){
        userService.logout(httpSession);
        return "redirect:/login";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Model model, HttpSession httpSession, HttpServletRequest request){
        List<User> usuarios = userService.findAll();
        model.addAttribute("users", usuarios);
        //request.setAttribute("users", usuarios);
        return "test";
    }
}
