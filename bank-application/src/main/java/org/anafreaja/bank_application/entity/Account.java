package org.anafreaja.bank_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Id
    private int account_id;
    private String account_type;
    private int balance;
    private LocalDateTime createdAt;
}
