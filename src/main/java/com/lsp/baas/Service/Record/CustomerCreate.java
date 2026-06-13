package com.lsp.baas.Service.Record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerCreate(
        @NotBlank(message = "FullName is required")
        @Size(max = 300)
        String fullName,

        @NotBlank(message = "CompanyName is required")
        @Size(max = 300)
        String companyName,

        @NotBlank(message = "DocNumber is required")
        @Size(max = 20)
        String docNumber,

        @NotBlank(message = "DocumentType is required")
        String documentType,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid.")
        @Size(max = 100)
        String email
) {
}
