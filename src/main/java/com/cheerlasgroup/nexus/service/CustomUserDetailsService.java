package com.cheerlasgroup.nexus.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cheerlasgroup.nexus.repository.UserRepository;
import com.cheerlasgroup.nexus.request.UserRequest;
import com.cheerlasgroup.nexus.entity.UserEntity;
import com.cheerlasgroup.nexus.entity.Role;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()).toArray(new String[0]))
                .build();
    }

    // Helper method to create users with roles
    public void createUser(UserRequest userRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(userRequest.getAuthorities().stream()
                .map(role -> new Role(role.getAuthority()))
                .collect(Collectors.toSet()));
        userRepository.save(user);
    }
}
