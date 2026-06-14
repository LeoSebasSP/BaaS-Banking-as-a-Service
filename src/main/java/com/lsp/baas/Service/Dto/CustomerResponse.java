package com.lsp.baas.Service.Dto;

import java.time.OffsetDateTime;

public record CustomerResponse(
        String fullName,
        String companyName,
        String docNumber,
        String documentType,
        String cuid,
        String email,
        Boolean isEnabled,
        Long creatorUser,
        Long updaterUser,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
