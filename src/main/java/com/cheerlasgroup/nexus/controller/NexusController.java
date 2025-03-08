package com.cheerlasgroup.nexus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nexus")
public class NexusController {

    @GetMapping("")
    public String hello() {
        return "Hello, Welcome to Cheerla's Nexus!";
    }
}
