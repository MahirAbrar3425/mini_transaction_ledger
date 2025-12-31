package com.tl.transaction_ledger.controller;

import com.tl.transaction_ledger.dto.CreateTransactionRequest;
import com.tl.transaction_ledger.dto.TransactionResponse;
import com.tl.transaction_ledger.model.transaction;
import com.tl.transaction_ledger.service.transactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for transaction-related operations.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
@RequiredArgsConstructor
public class transactionController {

    private final transactionService transactionService_;

    /**
     * Add a transaction to an account.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse addTransaction(
            @PathVariable Long accountId,
            @Valid @RequestBody CreateTransactionRequest request
    ) {
        transaction transaction_ = transactionService_.addTransaction(
                accountId,
                request.description(),
                request.amount(),
                request.type()
        );

        return mapToResponse(transaction_);
    }

    /**
     * Get all transactions for an account.
     */
    @GetMapping
    public List<TransactionResponse> getTransactions(@PathVariable Long accountId) {
        return transactionService_
                .getTransactions(accountId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TransactionResponse mapToResponse(transaction transaction_) {
        return new TransactionResponse(
                transaction_.getId(),
                transaction_.getDescription(),
                transaction_.getAmount(),
                transaction_.getType(),
                transaction_.getCreatedAt()
        );
    }
}
