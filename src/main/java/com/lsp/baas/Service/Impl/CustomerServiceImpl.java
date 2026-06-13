package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Customer;
import com.lsp.baas.Persistence.Repository.ICustomerRepository;
import com.lsp.baas.Service.ICustomerService;
import com.lsp.baas.Service.Record.CustomerCreate;
import com.lsp.baas.Service.Record.CustomerResponse;
import com.lsp.baas.Service.Record.Mapper.CustomerMapper;
import com.lsp.baas.Util.CuidGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
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
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public Page<CustomerResponse> findAllPage(Pageable pageable) {
        return null;
    }

    @Override
    public Slice<CustomerResponse> findAllSlice(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<CustomerResponse> findById(Long id) {
        return Optional.empty();
    }
}
