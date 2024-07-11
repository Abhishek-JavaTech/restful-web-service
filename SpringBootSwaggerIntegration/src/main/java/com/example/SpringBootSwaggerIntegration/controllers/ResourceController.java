package com.example.SpringBootSwaggerIntegration.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Operation(description = "api descr goes here",
            summary = "api summary goes here"
    )
    @GetMapping
    public String get() {
        return "Hello World!";
    }
}
