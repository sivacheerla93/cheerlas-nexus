package com.cheerlasgroup.nexus.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDetailsRequest {
    private String username;
    private String password;
    private Set<String> roles;
}
