package com.tl.transaction_ledger.controller;


import com.tl.transaction_ledger.dto.AccountResponse;
import com.tl.transaction_ledger.dto.CreateAccountRequest;
import com.tl.transaction_ledger.model.Account;
import com.tl.transaction_ledger.service.AccountService;
import com.tl.transaction_ledger.service.transactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for account-related operations.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final transactionService transactionService_;

    /**
     * Create a new account.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ) {
        System.out.println("CREATE ACCOUNT NAME = [" + request.name() + "]");
        Account account = accountService.createAccount(request.name());

        double balance=0;
        return new AccountResponse(
                account.getId(),
                account.getName(),
                balance);
    }


    @GetMapping("/{accountId}")
    public AccountResponse getAccount(@PathVariable Long accountId) {
        Account acc = accountService.getAccount(accountId);
        double balance = transactionService_.calculateBalance(accountId);
        return new AccountResponse(acc.getId(), acc.getName(), balance);
    }

    @GetMapping("/by-name")
    public AccountResponse getAccountByName(@RequestParam String name) {
        System.out.println("LOGIN NAME = [" + name + "]");
        Account acc = accountService.getAccountByName(name);
        double balance = transactionService_.calculateBalance(acc.getId());
        return new AccountResponse(acc.getId(), acc.getName(), balance);
    }





}
