package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.TransactionChannel;

import java.util.Optional;

public interface ITransactionChannelRepository extends IRepository<TransactionChannel, Long> {

    Optional<TransactionChannel> findByName(String name);
}
