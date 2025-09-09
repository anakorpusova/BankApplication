package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dtos.AccountItem;
import org.example.dtos.PostNewAccount;
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
    public ResponseEntity<List<AccountItem>> getAccountIndex() {
        List<AccountItem> items = accountService.getAllAccounts();
        return ResponseEntity.ok(items);
    }

    @PostMapping(value = {"/add", "/add/"})
    public ResponseEntity<Void> getAddAccount(@RequestBody PostNewAccount account) throws AccountDuplicationException {
        accountService.createAccount(account);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAccount(
            @PathVariable Long id,
            @RequestBody PostNewAccount updatedAccount) throws AccountNotFoundException, AccountDuplicationException {
        accountService.updateAccount(id, updatedAccount);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) throws AccountNotFoundException {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
