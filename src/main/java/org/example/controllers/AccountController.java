package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dtos.AccountItem;
import org.example.dtos.PostNewAccount;
import org.example.dtos.PostNewCheckingAccount;
import org.example.dtos.PostNewSavingsAccount;
import org.example.entities.Account;
import org.example.entities.CheckingAccount;
import org.example.entities.SavingsAccount;
import org.example.exceptions.AccountDuplicationException;
import org.example.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(value = {"/"})
        public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
        }

    @GetMapping(value = {"/{id}"})
        public Account getAccount(@PathVariable Long id){
            return accountService.getAccountById(id);
    }

    @PostMapping(value = {"/checking"})
        public CheckingAccount createChecking(@RequestBody PostNewCheckingAccount dto) throws AccountDuplicationException {
            return accountService.createCheckingAccount(dto);
        }

    @PostMapping(value = {"/savings"})
        public SavingsAccount createSavings(@RequestBody PostNewSavingsAccount dto){
            return  accountService.createSavingsAccount(dto);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account dto) {
        return accountService.updateAccount(id, dto);
    }

    @PutMapping("/checking/{id}")
    public CheckingAccount updateChecking(@PathVariable Long id, @RequestBody PostNewCheckingAccount dto) throws AccountNotFoundException, AccountDuplicationException {
        return accountService.updateCheckingAccount(id, dto);
    }

    @PutMapping("/savings/{id}")
    public SavingsAccount updateSavings(@PathVariable Long id, @RequestBody PostNewSavingsAccount dto) throws AccountNotFoundException {
        return accountService.updateSavingsAccount(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) throws AccountNotFoundException {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
