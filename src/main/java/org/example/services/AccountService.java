package org.example.services;

import org.example.dtos.AccountItem;
import org.example.dtos.PostNewAccount;
import org.example.dtos.PostNewCheckingAccount;
import org.example.dtos.PostNewSavingsAccount;
import org.example.entities.Account;
import org.example.entities.CheckingAccount;
import org.example.entities.SavingsAccount;
import org.example.exceptions.AccountDuplicationException;
import org.example.repositories.AccountRepository;
import org.example.utils.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    public CheckingAccount createCheckingAccount (PostNewCheckingAccount dto) throws AccountDuplicationException {
        CheckingAccount account = new CheckingAccount();
        account.setAccount_type("CHECKING");
        account.setBalance(0);
        account.setOverdraft_limit(dto.getOverdraftLimit());
        account.setDebit_card_linked(dto.isDebit_card_linked());
        return accountRepository.save(account);

    }

    public SavingsAccount createSavingsAccount (PostNewSavingsAccount dto){
        SavingsAccount account = new SavingsAccount();
        account.setAccount_type("SAVINGS");
        account.setBalance(0);
        account.setInterest_rate(dto.getInterest_rate());
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public void updateAccount(Long id, PostNewAccount updatedAccount) throws AccountDuplicationException, AccountNotFoundException {
        Account account = accountRepository.findById(updatedAccount.getId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (!account.getAccount_id().equals(updatedAccount.getId()) &&
                accountRepository.existsById(updatedAccount.getId())) {
            throw new AccountDuplicationException("Account already exists");
        }

        // Map updates (using mapper or manually)
        account.setBalance(updatedAccount.getBalance());

        accountRepository.save(account);
    }

    public void deleteAccount(Long id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        accountRepository.delete(account);
    }
}
