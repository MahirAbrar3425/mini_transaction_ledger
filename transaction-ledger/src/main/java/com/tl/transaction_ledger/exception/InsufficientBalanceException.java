package com.tl.transaction_ledger.exception;

/**
 * Thrown when a DEBIT transaction would cause negative balance.
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
