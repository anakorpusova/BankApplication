package org.anafreaja.bank_application.services;

import lombok.RequiredArgsConstructor;
import org.anafreaja.bank_application.dto.AuthRequest;
import org.anafreaja.bank_application.dto.CredentialRecord;
import org.anafreaja.bank_application.entity.UserCredential;
import org.anafreaja.bank_application.repository.UserCredentialRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    // returns boolean (valid/invalid)
    public boolean login(AuthRequest request) {
        var email = request.email().toLowerCase();
        var user = userCredentialRepository.findByEmail(email).orElse(null); // <— use findByEmail
        if (user != null) {
            passwordEncoder.matches(request.password(), user.getPassword());
        }
        return false;
    }

    public CredentialRecord createUserCredentials(AuthRequest request) {
        var entity = UserCredential.builder()
                .email(request.email().toLowerCase())
                .password(passwordEncoder.encode(request.password()))
                .role("USER")
                .enabled(true)
                .build();

        var saved = userCredentialRepository.save(entity);
        return new CredentialRecord(saved.getEmail(), saved.getRole());
    }
}