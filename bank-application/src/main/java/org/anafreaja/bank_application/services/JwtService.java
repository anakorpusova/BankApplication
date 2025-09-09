package org.anafreaja.bank_application.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.anafreaja.bank_application.config.JwtConfigProps;
import org.anafreaja.bank_application.entity.UserCredential;
import org.anafreaja.bank_application.repository.UserCredentialRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserCredentialRepository userCredentialRepository;
    private final JwtConfigProps jwtConfigProps;

    public String generateToken(String email) {
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findById(email);
        if (optionalUserCredential.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        UserCredential userCredential = optionalUserCredential.get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userCredential.getEmail());
        claims.put("role", userCredential.getRole());
        return generateToken(claims, userCredential);
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email").toString());
    }

    public boolean validateToken(String token, UserCredential userCredential) {
        final String email = extractEmail(token);
        return (email.equalsIgnoreCase(userCredential.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(Map<String, Object> claims, UserCredential userCredential) {
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(30).toMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] encodedKey = Decoders.BASE64.decode(jwtConfigProps.getSecret());
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
