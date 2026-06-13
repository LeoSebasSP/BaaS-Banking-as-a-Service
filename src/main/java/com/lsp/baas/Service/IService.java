package com.lsp.baas.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface IService<T, I> {
    T save(T t);
    Page<T> findAll(Pageable pageable);

    Slice<T> findAllSlice(Pageable pageable);
    Optional<T> findById(I i);
}
