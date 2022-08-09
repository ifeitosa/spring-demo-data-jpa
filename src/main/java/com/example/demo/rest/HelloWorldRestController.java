package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloWorldRestController {
    
    @GetMapping
    public String sayHelloWorld() {
        return "Hello, World!";
    }
}
