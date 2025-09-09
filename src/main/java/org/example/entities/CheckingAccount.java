package org.example.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CHECKING")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckingAccount extends Account{
    private int overdraft_limit;
    private boolean debit_card_linked;
}
