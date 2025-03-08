package com.cheerlasgroup.nexus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cheerlasgroup.nexus.request.UserRequest;
import com.cheerlasgroup.nexus.service.CustomUserDetailsService;

@RestController
@RequestMapping("/nexus")
public class UserController {

    private CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserRequest userRequest) {
        userDetailsService.createUser(userRequest);

        return HttpStatus.CREATED.toString();
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin access granted!";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User access granted!";
    }

}
