package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface ICustomerRepository extends IRepository<Customer, Long>{

    boolean existsByCuid(String cuid);

    @Override
    @EntityGraph(attributePaths = {"documentType"})
    List<Customer> findAll();
}
