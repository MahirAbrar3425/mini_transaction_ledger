package com.tl.transaction_ledger.repository;

import com.tl.transaction_ledger.model.transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface transactionRepository
        extends JpaRepository<transaction, Long> {
    List<transaction> findByAccountId(Long accountId);
}
