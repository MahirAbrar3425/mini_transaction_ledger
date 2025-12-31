package com.tl.transaction_ledger.dto;

import com.tl.transaction_ledger.model.TransactionType;

import java.time.LocalDateTime;

/**
 * Response sent back to client after transaction operations.
 */
public record TransactionResponse(
        Long id,
        String description,
        double amount,
        TransactionType type,
        LocalDateTime createdAt
) {}
