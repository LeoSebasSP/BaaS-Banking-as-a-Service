package com.lsp.baas.Service.Dto.Mapper;

import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Persistence.Entity.Customer;
import com.lsp.baas.Persistence.Entity.DocumentType;
import com.lsp.baas.Persistence.Repository.IDocumentTypeRepository;
import com.lsp.baas.Service.Dto.CustomerCreate;
import com.lsp.baas.Service.Dto.CustomerResponse;
import com.lsp.baas.Service.Dto.CustomerUpdate;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    @Autowired
    protected IDocumentTypeRepository iDocumentTypeRepository;

    @Mapping(target = "documentType", source = "documentType")
    public abstract Customer toEntity(CustomerCreate customerCreateDto);

    @Mapping(target = "documentType", source = "documentType.name")
    public abstract CustomerResponse toResponse(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromPatch(CustomerUpdate customerUpdate, @MappingTarget Customer customer);

    protected DocumentType mapStringToDocumentType(String documentTypeName) {
        return iDocumentTypeRepository.findByName(documentTypeName.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException.DocumentTypeNotFoundException(documentTypeName));
    }
}
