package com.atrezzo.manager.infrastructure.configuration.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping(value = "demo")
    public String demo() {
        return "weolcome to spring security";
    }
}
