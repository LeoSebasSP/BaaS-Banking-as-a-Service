package com.lsp.baas.Service;

import com.lsp.baas.Service.Record.CustomerCreate;
import com.lsp.baas.Service.Record.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ICustomerService{

    CustomerResponse save(CustomerCreate customerCreateDto);

    List<CustomerResponse> findAll();
    Page<CustomerResponse> findAllPage(Pageable pageable);

    Slice<CustomerResponse> findAllSlice(Pageable pageable);
    Optional<CustomerResponse> findById(Long id);
}
