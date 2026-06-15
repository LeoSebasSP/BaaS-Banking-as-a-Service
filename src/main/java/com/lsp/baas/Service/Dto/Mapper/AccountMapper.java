package com.lsp.baas.Service.Dto.Mapper;

import com.lsp.baas.Exception.ResourceDisabledException;
import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Persistence.Entity.*;
import com.lsp.baas.Persistence.Repository.IAccountTypeRepository;
import com.lsp.baas.Persistence.Repository.ICurrencyRepository;
import com.lsp.baas.Persistence.Repository.ICustomerRepository;
import com.lsp.baas.Persistence.Repository.IDocumentTypeRepository;
import com.lsp.baas.Service.Dto.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected ICustomerRepository iCustomerRepository;

    @Autowired
    protected IAccountTypeRepository iAccountTypeRepository;

    @Autowired
    protected ICurrencyRepository iCurrencyRepository;

    @Mapping(target = "customer", source = "cuid")
    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "currency", source = "currency")
    public abstract Account toEntity(AccountCreate accountCreate);

    @Mapping(target = "cuid", source = "customer.cuid")
    @Mapping(target = "accountType", source = "accountType.name")
    @Mapping(target = "currency", source = "currency.name")
    public abstract AccountResponse toResponse(Account account);

    protected Customer mapStringToCustomer(String cuid) {
        Customer customer = iCustomerRepository.findByCuid(cuid)
                .orElseThrow(() -> new ResourceNotFoundException.CustomerNotFoundException(cuid));
        if (!customer.getIsEnabled()){
            throw new ResourceDisabledException.CustomerDisabledException(cuid);
        }
        return customer;
    }

    protected AccountType mapStringToAccountType(String accountType) {
        return iAccountTypeRepository.findByName(accountType.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException.AccountTypeNotFoundException(accountType));
    }

    protected Currency mapStringToCurrency(String currency) {
        return iCurrencyRepository.findByName(currency.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException.CurrencyNotFoundException(currency));
    }
}
