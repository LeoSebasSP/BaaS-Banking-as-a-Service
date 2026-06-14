package com.lsp.baas.Controller.ResponseDto;

public record Meta(
        int totalPages,
        int currentPage,
        int size
) {
}
