package com.lsp.baas.Service.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record AccountResponse(
        String cuid,
        String accountNumber,
        String accountType,
        String currency,
        String status,
        Long creatorUser,
        Long updaterUser,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
