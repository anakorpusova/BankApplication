package org.example.services;

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

    public Account updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existing -> {
                    // update common fields
                    existing.setBalance(updatedAccount.getBalance());

                    // update subclass-specific fields
                    if (existing instanceof CheckingAccount checking &&
                            updatedAccount instanceof CheckingAccount newChecking) {
                        checking.setOverdraft_limit(newChecking.getOverdraft_limit());
                    } else if (existing instanceof SavingsAccount savings &&
                            updatedAccount instanceof SavingsAccount newSavings) {
                        savings.setInterest_rate(newSavings.getInterest_rate());
                    }

                    return accountRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public CheckingAccount updateCheckingAccount(Long id, PostNewCheckingAccount dto) throws AccountDuplicationException, AccountNotFoundException {
        CheckingAccount existing = (CheckingAccount) accountRepository.findById(id)
                .orElseThrow(() -> new org.example.exceptions.AccountNotFoundException("Checking account not found"));
        existing.setOverdraft_limit(dto.getOverdraftLimit());
        existing.setDebit_card_linked(dto.isDebit_card_linked());
        return accountRepository.save(existing);
    }

    public SavingsAccount updateSavingsAccount(Long id, PostNewSavingsAccount dto) throws AccountNotFoundException {
        SavingsAccount existing = (SavingsAccount) accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Savings account not found"));
        existing.setInterest_rate(dto.getInterest_rate());

        return accountRepository.save(existing);
    }

    public void deleteAccount(Long id) throws AccountNotFoundException{
        if(!accountRepository.existsById(id)){
            throw new AccountNotFoundException("Account not found");
        }
        accountRepository.deleteById(id);
    }
}
