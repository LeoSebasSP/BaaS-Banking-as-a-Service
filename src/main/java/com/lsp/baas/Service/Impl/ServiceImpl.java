package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public abstract class ServiceImpl<T, I> implements IService<T, I> {

    protected abstract IRepository<T,I> getRepository();

    @Override
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Slice<T> findAllSlice(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Optional<T> findById(I i) {
        return getRepository().findById(i);
    }
}
