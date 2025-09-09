package org.anafreaja.bank_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckingAccount extends Account{
    @Id
    private int account_id;
    private int overdraft_limit;
    private boolean debit_card_linked;
}