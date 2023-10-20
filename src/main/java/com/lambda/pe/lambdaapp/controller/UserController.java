package com.lambda.pe.lambdaapp.controller;

import com.lambda.pe.lambdaapp.service.UserService;
import org.springframework.stereotype.Controller;

@Controller("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
