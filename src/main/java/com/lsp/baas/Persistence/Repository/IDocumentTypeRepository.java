package com.lsp.baas.Persistence.Repository;

import com.lsp.baas.Persistence.Entity.DocumentType;

import java.util.Optional;

public interface IDocumentTypeRepository extends IRepository<DocumentType, Long> {

    Optional<DocumentType> findByName(String name);
}
