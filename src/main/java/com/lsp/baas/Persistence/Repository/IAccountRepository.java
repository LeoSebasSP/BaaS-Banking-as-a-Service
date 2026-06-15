package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.Account;
import com.lsp.baas.Persistence.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAccountRepository extends IRepository<Account, Long> {

    @Query(value = "SELECT nextval('seq_account_number_correlative')", nativeQuery = true)
    Long getNextAccountNumberSequence();

    @Override
    @EntityGraph(attributePaths = {"customer","accountType","currency"})
    Page<Account> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"customer","accountType","currency"})
    Optional<Account> findByAccountNumber(String accountNumber);
}
