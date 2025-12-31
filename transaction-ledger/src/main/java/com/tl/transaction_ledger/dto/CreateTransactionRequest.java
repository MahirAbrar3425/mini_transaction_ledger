package com.tl.transaction_ledger.dto;



import com.tl.transaction_ledger.model.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request body for creating a transaction.
 */
public record CreateTransactionRequest(

        @NotBlank(message = "Description must not be empty")
        String description,

        @Min(value = 1, message = "Amount must be greater than zero")
        double amount,

        @NotNull(message = "Transaction type is required")
        TransactionType type

) {}
