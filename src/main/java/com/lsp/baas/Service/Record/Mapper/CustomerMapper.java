package com.lsp.baas.Service.Record.Mapper;

import com.lsp.baas.Persistence.Entity.Customer;
import com.lsp.baas.Persistence.Entity.DocumentType;
import com.lsp.baas.Persistence.Repository.IDocumentTypeRepository;
import com.lsp.baas.Service.Record.CustomerCreate;
import com.lsp.baas.Service.Record.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    @Autowired
    protected IDocumentTypeRepository iDocumentTypeRepository;

    @Mapping(target = "documentType", source = "documentType")
    public abstract Customer toEntity(CustomerCreate customerCreateDto);

    @Mapping(target = "documentType", source = "documentType.name")
    public abstract CustomerResponse toResponse(Customer customer);

    protected DocumentType mapStringToDocumentType(String documentTypeName) {
        return iDocumentTypeRepository.findByName(documentTypeName)
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado: " + documentTypeName));
    }
}
