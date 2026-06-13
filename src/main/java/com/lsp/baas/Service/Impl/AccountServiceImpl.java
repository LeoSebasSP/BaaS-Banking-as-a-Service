package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Account;
import com.lsp.baas.Persistence.Repository.IAccountRepository;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<Account, Long> implements IAccountService {

    private final IAccountRepository repository;

    @Override
    protected IRepository<Account, Long> getRepository() {
        return repository;
    }
}
