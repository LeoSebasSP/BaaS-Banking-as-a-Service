package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Repository.IProductRepository;
import com.lsp.baas.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository repository;

}
