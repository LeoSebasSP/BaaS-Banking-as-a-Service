package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.Currency;

import java.util.Optional;

public interface ICurrencyRepository extends IRepository<Currency, Long> {

    Optional<Currency> findByName(String name);
}
