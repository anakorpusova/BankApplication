package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dtos.AccountItem;
import org.example.dtos.PostNewAccount;
import org.example.exceptions.AccountDuplicationException;
import org.example.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(value = {"/"})
    public ResponseEntity<List<AccountItem>> getStoreIndex() {
        List<AccountItem> items = accountService.getAllAccounts();
        return ResponseEntity.ok(items);
    }

    @PostMapping(value = {"/add", "/add/"})
    public ResponseEntity<Void> getStoreAddProduct(@RequestBody PostNewAccount account) throws AccountDuplicationException {
        accountService.createAccount(account);
        return ResponseEntity.noContent().build();
    }
}
