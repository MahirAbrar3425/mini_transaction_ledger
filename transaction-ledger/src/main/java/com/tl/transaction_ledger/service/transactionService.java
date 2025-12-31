package com.tl.transaction_ledger.service;


import com.tl.transaction_ledger.exception.InsufficientBalanceException;
import com.tl.transaction_ledger.model.Account;
import com.tl.transaction_ledger.model.transaction;
import com.tl.transaction_ledger.model.TransactionType;
import com.tl.transaction_ledger.repository.transactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Handles business logic related to transactions.
 */
@Service
@RequiredArgsConstructor
public class transactionService {

    private final transactionRepository transactionRepository;
    private final AccountService accountService;

    public List<transaction> getTransactions(Long accountId) {
        // Validate account exists
        accountService.getAccount(accountId);

        return transactionRepository.findByAccountId(accountId);
    }

    /**
     * Adds a transaction to an account.
     * This method is transactional to ensure
     * atomic balance validation + save.
     */
    @Transactional
    public transaction addTransaction(
            Long accountId,
            String description,
            double amount,
            TransactionType type
    ) {
        Account account = accountService.getAccount(accountId);

        double currentBalance = calculateBalance(accountId);

        // Prevent negative balance for DEBIT
        if (type == TransactionType.DEBIT && currentBalance < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Available: " + currentBalance);
        }

        transaction Transaction = transaction.builder()
                .description(description)
                .amount(amount)
                .type(type)
                .account(account)
                .build();

        return transactionRepository.save(Transaction);
    }

    /**
     * Calculates the current balance of an account
     * based on all its transactions.
     */
    public double calculateBalance(Long accountId) {
        List<transaction> Transactions =
                transactionRepository.findByAccountId(accountId);

        double balance = 0;

        for (transaction Transaction : Transactions) {
            if (Transaction.getType() == TransactionType.CREDIT) {
                balance += Transaction.getAmount();
            } else {
                balance -= Transaction.getAmount();
            }
        }

        return balance;
    }
}
