package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Entity.Affiliation;
import com.lsp.baas.Persistence.Repository.IAffiliationRepository;
import com.lsp.baas.Persistence.Repository.IRepository;
import com.lsp.baas.Service.IAffiliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffiliationServiceImpl extends ServiceImpl<Affiliation, Long> implements IAffiliationService {
    
    private final IAffiliationRepository repository;
    
    @Override
    protected IRepository<Affiliation, Long> getRepository() {
        return repository;
    }
}
