package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Repository.IMovementRepository;
import com.lsp.baas.Service.IMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements IMovementService {

    private final IMovementRepository repository;

}
