package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckingAccount extends Account{
    private int overdraft_limit;
    private boolean debit_card_linked;
}
