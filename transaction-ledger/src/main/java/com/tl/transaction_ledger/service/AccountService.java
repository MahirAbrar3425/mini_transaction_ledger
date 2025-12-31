package com.tl.transaction_ledger.service;


import com.tl.transaction_ledger.model.Account;
import com.tl.transaction_ledger.repository.AccountRepository;
import com.tl.transaction_ledger.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Handles business logic related to accounts.
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Creates a new account.
     */
    @Transactional
    public Account createAccount(String name) {
        String sanitized = name.trim();
        Account account = Account.builder()
                .name(sanitized)
                .build();
        return accountRepository.saveAndFlush(account);
    }

    /**
     * Fetches an account by ID or throws exception if not found.
     */
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found with id: " + accountId));
    }

    public Account getAccountByName(String name) {
        String sanitized = name.trim();
        return accountRepository.findByNameIgnoreCase(sanitized)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found with name: " + name));
    }

}
