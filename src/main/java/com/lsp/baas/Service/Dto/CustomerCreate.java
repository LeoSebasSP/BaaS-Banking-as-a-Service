package com.lsp.baas.Service.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerCreate(
        @NotBlank(message = "FullName is required")
        @Size(min = 1, max = 300, message = "FullName cannot be empty and max 300 chars")
        String fullName,

        @NotBlank(message = "CompanyName is required")
        @Size(min = 1, max = 300, message = "CompanyName cannot be empty and max 300 chars")
        String companyName,

        @NotBlank(message = "DocNumber is required")
        @Size(min = 1, max = 20, message = "DocNumber cannot be empty and max 20 chars")
        String docNumber,

        @NotBlank(message = "DocumentType is required")
        String documentType,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid.")
        @Size(max = 100, message = "Email cannot be empty and max 100 chars")
        String email
) {
}
