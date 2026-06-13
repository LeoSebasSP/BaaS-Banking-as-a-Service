package com.lsp.baas.Persistence.Repository.EntityManager.Impl;

import com.lsp.baas.Persistence.Entity.Customer;
import com.lsp.baas.Persistence.Repository.EntityManager.ICustomerDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements ICustomerDao {

    private final EntityManager entityManager;

    @Override
    public void save(Customer customer) {
        this.entityManager.persist(customer);
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = this.entityManager.createQuery("FROM Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        return this.entityManager.find(Customer.class, id);
    }

    @Override
    public void update(Customer customer) {
        this.entityManager.merge(customer);
    }
}
