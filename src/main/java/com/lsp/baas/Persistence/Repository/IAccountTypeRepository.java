package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.AccountType;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAccountTypeRepository extends IRepository<AccountType, Long> {

    Optional<AccountType> findByName(String name);

}
