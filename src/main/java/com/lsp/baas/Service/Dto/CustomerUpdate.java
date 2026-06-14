package com.lsp.baas.Service.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerUpdate(
        @Size(min = 1, max = 300, message = "FullName cannot be empty and max 300 chars")
        String fullName,

        @Size(min = 1, max = 300, message = "CompanyName cannot be empty and max 300 chars")
        String companyName,

        @Size(min = 1, max = 20, message = "DocNumber cannot be empty and max 20 chars")
        String docNumber,

        String documentType,

        @Email(message = "Email must be valid.")
        @Size(max = 100, message = "Email cannot be empty and max 100 chars")
        String email
) {
}
