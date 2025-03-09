package com.cheerlasgroup.nexus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cheerlasgroup.nexus.request.UserDetailsRequest;
import com.cheerlasgroup.nexus.service.UserDetailsService;

@RestController
@RequestMapping("/nexus")
public class UserDetailsController {

    private UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody UserDetailsRequest userRequest) {
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
