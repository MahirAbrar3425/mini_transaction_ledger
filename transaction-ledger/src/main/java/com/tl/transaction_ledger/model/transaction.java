package com.tl.transaction_ledger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a financial transaction.
 * Each transaction belongs to exactly one account.
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class transaction {

    /**
     * Primary key for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Short description of the transaction.
     * Example: "Salary", "Groceries"
     */
    @Column(nullable = false)
    private String description;

    /**
     * Transaction amount.
     * Always stored as a positive number.
     */
    @Column(nullable = false)
    private double amount;

    /**
     * Type of transaction.
     * DEBIT reduces balance, CREDIT increases balance.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    /**
     * Timestamp when the transaction was created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * Many transactions belong to one account.
     * This creates a foreign key: account_id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    /**
     * Automatically set timestamp before saving to database.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}


