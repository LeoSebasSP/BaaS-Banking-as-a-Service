package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Account;
import com.lsp.baas.Persistence.Entity.Enum.AccountStatus;
import com.lsp.baas.Persistence.Repository.IAccountRepository;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.Dto.AccountCreate;
import com.lsp.baas.Service.Dto.AccountResponse;
import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import com.lsp.baas.Service.Dto.Mapper.AccountMapper;
import com.lsp.baas.Service.IAccountService;
import com.lsp.baas.Util.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository repository;
    private final AccountMapper mapper;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    @Transactional
    public AccountResponse save(AccountCreate accountCreate) {
        Account account = mapper.toEntity(accountCreate);
        account.setAccountNumber(accountNumberGenerator.generateAccounNumber(
                account.getAccountType(),
                account.getCurrency(),
                repository.getNextAccountNumberSequence()
        ));
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setCreatorUser(1L);
        account.setCreatedAt(OffsetDateTime.now());

        return mapper.toResponse(repository.save(account));
    }

    @Override
    public Page<AccountResponse> findAllPage(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Optional<AccountResponse> findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber).map(mapper::toResponse);
    }
}
