package org.example.utils;

import org.example.dtos.PostNewAccount;
import org.example.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account postNewAccount(PostNewAccount account){
        return Account.builder()
                .account_type(account.getAccount_type())
                .balance(account.getBalance())
                .build();
    }
}
