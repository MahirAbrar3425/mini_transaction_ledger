package com.tl.transaction_ledger.dto;



import jakarta.validation.constraints.NotBlank;

/**
 * Request body for creating a new account.
 */
public record CreateAccountRequest(

        @NotBlank(message = "Account name must not be empty")
        String name

) {}
