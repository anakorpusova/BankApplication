package org.anafreaja.bank_application.service;

import lombok.RequiredArgsConstructor;
import org.anafreaja.bank_application.entity.UserCredentials;
import org.anafreaja.bank_application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository repo; private final PasswordEncoder encoder;

    public void register(String email, String raw) {
        var key = email.toLowerCase().trim();
        if (repo.existsById(key)) throw new IllegalArgumentException("Email already registered");
        repo.save(UserCredentials.builder().email(key).password(encoder.encode(raw)).build());
    }
    public boolean check(String email, String raw) {
        return repo.findById(email.toLowerCase().trim())
                .filter(UserCredentials::isEnabled)
                .map(u -> encoder.matches(raw, u.getPassword()))
                .orElse(false);
    }
}
