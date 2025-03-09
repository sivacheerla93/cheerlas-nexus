package com.cheerlasgroup.nexus.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cheerlasgroup.nexus.repository.RoleDetailsRepository;
import com.cheerlasgroup.nexus.repository.UserDetailsRepository;
import com.cheerlasgroup.nexus.request.UserDetailsRequest;
import com.cheerlasgroup.nexus.entity.UserDetailsEntity;
import com.cheerlasgroup.nexus.entity.RoleDetailsEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDetailsRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private RoleDetailsRepository roleRepository;

    public UserDetailsService(UserDetailsRepository userRepository, PasswordEncoder passwordEncoder,
            RoleDetailsRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(RoleDetailsEntity::getName)
                        .collect(Collectors.toList()).toArray(new String[0]))
                .build();
    }

    // Helper method to create users with roles. This needs few validations and
    // error handling.
    public void createUser(UserDetailsRequest userRequest) {
        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setUsername(userRequest.getUsername());
        userDetails.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Set<RoleDetailsEntity> roles = new HashSet<RoleDetailsEntity>();

        userRequest.getRoles().forEach(role -> {
            roleRepository.findByName(role).ifPresentOrElse(
                    roleDetailsEntity -> roles.add(roleDetailsEntity),
                    () -> roles.add(new RoleDetailsEntity(role)));
        });

        userDetails.setRoles(roles);
        userRepository.save(userDetails);
    }
}
