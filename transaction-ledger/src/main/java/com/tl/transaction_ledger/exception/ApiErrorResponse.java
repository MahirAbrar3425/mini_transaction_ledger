package com.tl.transaction_ledger.exception;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Standard error response returned by the API.
 */
public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<String> details
) {
}