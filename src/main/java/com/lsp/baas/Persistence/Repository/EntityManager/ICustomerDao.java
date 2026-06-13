package com.lsp.baas.Persistence.Repository.EntityManager;

import com.lsp.baas.Persistence.Entity.Customer;

import java.util.List;

public interface ICustomerDao {
    void save(Customer customer);
    List<Customer> findAll();
    Customer findById(Long id);
    void update(Customer customer);
}
