package com.tl.transaction_ledger.dto;

public record AccountResponse(
        Long id,
        String name,
        double balance) {}