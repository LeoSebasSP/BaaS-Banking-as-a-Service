package com.lsp.baas.Service.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreate(
        @NotBlank(message = "Customer's CUID is required")
        @Size(min = 1, max = 30, message = "Customer's CUID cannot be empty and max 30 chars")
        String cuid,

        @NotBlank(message = "AccountType is required")
        @Size(min = 1, max = 50, message = "AccountType cannot be empty and max 50 chars")
        String accountType,

        @NotBlank(message = "Currency is required")
        @Size(min = 1, max = 50, message = "Currency cannot be empty and max 50 chars")
        String currency
) {
}
