package org.example.repositories;

import org.example.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long id);
    boolean existsById(Long id);
}
