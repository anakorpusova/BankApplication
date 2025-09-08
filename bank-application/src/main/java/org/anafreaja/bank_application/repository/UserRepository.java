package org.anafreaja.bank_application.repository;



import org.anafreaja.bank_application.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserCredentials,String> {
Optional<UserCredentials> findByEmail (String email);
}
