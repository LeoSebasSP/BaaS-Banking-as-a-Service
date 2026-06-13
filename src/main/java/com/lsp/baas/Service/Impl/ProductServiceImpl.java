package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Product;
import com.lsp.baas.Persistence.Repository.IProductRepository;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<Product, Long> implements IProductService {

    private final IProductRepository repository;

    @Override
    protected IRepository<Product, Long> getRepository() {
        return repository;
    }
}
