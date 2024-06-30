package com.eCommerceApp.backend.service;

import com.eCommerceApp.backend.model.User;
import com.eCommerceApp.backend.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userWithRole = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("not found user with email: " + email));

        Set<GrantedAuthority> auth = userWithRole.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(userWithRole.getEmail(),
                userWithRole.getPassword(), auth);

    }

}
