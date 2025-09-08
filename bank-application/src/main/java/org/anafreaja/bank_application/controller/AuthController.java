package org.anafreaja.bank_application.controller;


import lombok.RequiredArgsConstructor;
import org.anafreaja.bank_application.dto.LoginRequest;
import org.anafreaja.bank_application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController @RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService users;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest req) {
        return users.check(req.email(), req.password())
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(401).build();
    }
}