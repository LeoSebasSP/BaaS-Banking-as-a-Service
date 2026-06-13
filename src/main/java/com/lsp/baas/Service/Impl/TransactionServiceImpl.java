package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Transaction;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Persistence.Repository.ITransactionRepository;
import com.lsp.baas.Service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl extends ServiceImpl<Transaction, Long> implements ITransactionService {

    private final ITransactionRepository repository;

    @Override
    protected IRepository<Transaction, Long> getRepository() {
        return repository;
    }
}
