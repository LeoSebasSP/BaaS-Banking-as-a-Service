package com.lsp.baas.Service.Impl;

import com.lsp.baas.Persistence.Repository.IAffiliationRepository;
import com.lsp.baas.Service.IAffiliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AffiliationServiceImpl implements IAffiliationService {
    
    private final IAffiliationRepository repository;

}
