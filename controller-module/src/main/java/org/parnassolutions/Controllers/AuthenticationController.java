package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.AuthenticationRequest;
import org.parnassolutions.DTOs.RegisterRequest;
import org.parnassolutions.Services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
