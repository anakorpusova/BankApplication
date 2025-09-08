package org.anafreaja.bank_application.repository;

import org.anafreaja.bank_application.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<UserCredentials, String> {
}
