package org.anafreaja.bank_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class SavingsAccount extends Account{
    @Id
    private int account_id;
    private float interest_rate;
}
