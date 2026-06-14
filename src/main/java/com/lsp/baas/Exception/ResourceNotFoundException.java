package com.lsp.baas.Exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String s) {
        super(s);
    }

    public static class CustomerNotFoundException extends ResourceNotFoundException {
        public CustomerNotFoundException(String cuid) {
            super("CUID [" + cuid + "] not found.");
        }
    }

    public static class DocumentTypeNotFoundException extends ResourceNotFoundException {
        public DocumentTypeNotFoundException(String documentType) {
            super("DocumentType [" + documentType + "] not found.");
        }
    }
}