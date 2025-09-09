package org.anafreaja.bank_application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_credentials")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserCredential implements UserDetails {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private String role = "USER";

    @Builder.Default
    private boolean enabled = true;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  "USER" or "ADMIN"
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return enabled; }
}
