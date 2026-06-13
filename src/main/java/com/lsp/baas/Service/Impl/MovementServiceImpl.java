package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Movement;
import com.lsp.baas.Persistence.Repository.IMovementRepository;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.IMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl extends ServiceImpl<Movement, Long> implements IMovementService {

    private final IMovementRepository repository;

    @Override
    protected IRepository<Movement, Long> getRepository() {
        return repository;
    }
}
