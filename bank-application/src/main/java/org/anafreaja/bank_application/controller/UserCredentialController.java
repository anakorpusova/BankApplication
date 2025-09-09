package org.anafreaja.bank_application.controller;

import java.net.URI;

import lombok.RequiredArgsConstructor;
import org.anafreaja.bank_application.dto.AuthRequest;
import org.anafreaja.bank_application.dto.CredentialRecord;
import org.anafreaja.bank_application.services.UserCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserCredentialController {

    private final UserCredentialService userCredentialService;

    @PostMapping("/register")
    public ResponseEntity<CredentialRecord> register(@RequestBody AuthRequest request) {
        var saved = userCredentialService.createUserCredentials(request); // returns CredentialRecord
        var location = URI.create("/api/auth/users/" + saved.email());
        return ResponseEntity.created(location).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        boolean ok = userCredentialService.login(request);
        return ok
                ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}



