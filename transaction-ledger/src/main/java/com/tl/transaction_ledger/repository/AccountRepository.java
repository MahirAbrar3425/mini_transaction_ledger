package com.tl.transaction_ledger.repository;


import com.tl.transaction_ledger.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Account entity.
 * Provides CRUD operations automatically.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNameIgnoreCase(String name);

}
