package org.example.services;

import org.example.dtos.AccountItem;
import org.example.dtos.PostNewAccount;
import org.example.entities.Account;
import org.example.exceptions.AccountDuplicationException;
import org.example.repositories.AccountRepository;
import org.example.utils.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    public void createAccount (PostNewAccount account) throws AccountDuplicationException {
        if (accountRepository.existsById(account.getId())){
            throw new AccountDuplicationException(("Account already exists"));
        }
        Account newAccount = accountMapper.postNewAccount(account);
        accountRepository.save(newAccount);
    }

    public List<AccountItem> getAllAccounts() {
        List<AccountItem> items = new ArrayList<>();
        accountRepository.findAll().forEach(item ->{
            items.add(new AccountItem(
                    Math.toIntExact(item.getAccount_id()),
                    item.getAccount_type(),
                    item.getBalance()));
        });
        return items;
    }
}
