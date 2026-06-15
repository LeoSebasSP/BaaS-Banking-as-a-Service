package com.lsp.baas.Service;

import com.lsp.baas.Service.Dto.AccountCreate;
import com.lsp.baas.Service.Dto.AccountResponse;
import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAccountService{
    AccountResponse save(AccountCreate accountCreate);

    Page<AccountResponse> findAllPage(Pageable pageable);

    Optional<AccountResponse> findByAccountNumber(String accountNumber);
}
