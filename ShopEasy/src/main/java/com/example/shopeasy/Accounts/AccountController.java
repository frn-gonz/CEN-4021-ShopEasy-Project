package com.example.shopeasy.Accounts;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountsRepository AccountsRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return AccountsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@PathVariable Integer id) {
        return AccountsRepository.findById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return AccountsRepository.save(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(
            @PathVariable Integer id,
            @RequestBody Account updatedAccount) {

        updatedAccount.setAccountId(id);
        return AccountsRepository.save(updatedAccount);
    }


    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Integer id) {
        AccountsRepository.deleteById(id);
    }
}
