package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends IRepository<Customer, Long>{

    boolean existsByCuid(String cuid);

    @Override
    @EntityGraph(attributePaths = {"documentType"})
    Page<Customer> findAll(Pageable pageable);


    @EntityGraph(attributePaths = {"documentType"})
    Optional<Customer> findByCuid(String cuid);
}
