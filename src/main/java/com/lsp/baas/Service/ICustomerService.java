package com.lsp.baas.Service;

import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import com.lsp.baas.Service.Dto.CustomerUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ICustomerService{

    CustomerResponse save(CustomerCreate customerCreateDto);

    Page<CustomerResponse> findAllPage(Pageable pageable);

    Optional<CustomerResponse> findByCuid(String cuid);

    CustomerResponse update(CustomerUpdate customerUpdate, String cuid);

    CustomerResponse disable(String cuid);
}