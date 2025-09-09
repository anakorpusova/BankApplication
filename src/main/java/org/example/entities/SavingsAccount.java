package org.example.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SAVINGS")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class SavingsAccount extends Account{
    private float interest_rate;
}
