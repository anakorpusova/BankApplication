package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostNewCheckingAccount {
    private int overdraftLimit;
    private boolean debit_card_linked;
}
