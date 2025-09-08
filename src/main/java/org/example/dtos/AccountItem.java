package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountItem {
    private int id;
    private String account_type;
    private float balance;

    public AccountItem(Long accountId, String accountType, Object o, float balance) {
    }
}
