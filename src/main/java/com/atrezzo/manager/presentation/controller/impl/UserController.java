package com.atrezzo.manager.presentation.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/")
    public Integer testURI() {
        return 1220;
    }
}
