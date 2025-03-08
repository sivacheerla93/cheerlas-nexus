package com.cheerlasgroup.nexus.request;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest extends User {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;

    public UserRequest(String username, String password,
            Set<GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

}
