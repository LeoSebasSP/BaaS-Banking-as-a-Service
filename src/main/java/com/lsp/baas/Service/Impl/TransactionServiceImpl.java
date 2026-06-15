package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Repository.ITransactionRepository;
import com.lsp.baas.Service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionRepository repository;

}
