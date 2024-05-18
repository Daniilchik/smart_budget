package org.parnassolutions.Services;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.AuthenticationRequest;
import org.parnassolutions.DTOs.RegisterRequest;
import org.parnassolutions.Entities.User;
import org.parnassolutions.Enums.Role;
import org.parnassolutions.Repositories.UserRepository;
import org.parnassolutions.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @NotNull
    @Transactional
    public String register(@NotNull RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    @NotNull
    @Transactional
    public String authenticate(@NotNull AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));

        return jwtService.generateToken(user);
    }
}
