package org.example.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
