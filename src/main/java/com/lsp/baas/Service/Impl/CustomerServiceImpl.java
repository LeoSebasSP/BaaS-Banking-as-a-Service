package com.lsp.baas.Service.Impl;

import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Persistence.Entity.Customer;
import com.lsp.baas.Persistence.Repository.ICustomerRepository;
import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import com.lsp.baas.Service.Dto.CustomerUpdate;
import com.lsp.baas.Service.Dto.Mapper.CustomerMapper;
import com.lsp.baas.Service.ICustomerService;
import com.lsp.baas.Util.CuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repository;
    private final CustomerMapper mapper;
    private final CuidGenerator cuidGenerator;

    @Override
    @Transactional
    public CustomerResponse save(CustomerCreate customerCreateDto) {
        Customer customer = mapper.toEntity(customerCreateDto);

        String cuid;
        do {
            cuid = cuidGenerator.generateCuid();
        } while (repository.existsByCuid(cuid));
        customer.setCuid(cuid);
        customer.setIsEnabled(true);
        customer.setCreatorUser(1L);
        customer.setCreatedAt(OffsetDateTime.now());

        return mapper.toResponse(repository.save(customer));
    }

    @Override
    public Page<CustomerResponse> findAllPage(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Optional<CustomerResponse> findByCuid(String cuid) {
        return repository.findByCuid(cuid).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public CustomerResponse update(CustomerUpdate customerUpdate, String cuid) {
        Customer customerBd = repository.findByCuid(cuid).orElseThrow(() -> new ResourceNotFoundException.CustomerNotFoundException(cuid));
        mapper.updateEntityFromPatch(customerUpdate, customerBd);
        return mapper.toResponse(repository.save(customerBd));
    }

    @Override
    @Transactional
    public CustomerResponse disable(String cuid) {
        Customer customerBd = repository.findByCuid(cuid).orElseThrow(() -> new ResourceNotFoundException.CustomerNotFoundException(cuid));
        customerBd.setIsEnabled(false);
        return mapper.toResponse(repository.save(customerBd));
    }

    @Override
    @Transactional
    public CustomerResponse enable(String cuid) {
        Customer customerBd = repository.findByCuid(cuid).orElseThrow(() -> new ResourceNotFoundException.CustomerNotFoundException(cuid));
        customerBd.setIsEnabled(true);
        return mapper.toResponse(repository.save(customerBd));
    }
}
