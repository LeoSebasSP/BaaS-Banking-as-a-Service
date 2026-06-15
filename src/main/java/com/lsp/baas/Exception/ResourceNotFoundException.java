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

    public static class AccountTypeNotFoundException extends ResourceNotFoundException {
        public AccountTypeNotFoundException(String accountType) {
            super("AccountType [" + accountType + "] not found.");
        }
    }

    public static class CurrencyNotFoundException extends ResourceNotFoundException {
        public CurrencyNotFoundException(String currency) {
            super("Currency [" + currency + "] not found.");
        }
    }

    public static class TransactionChannelNotFoundException extends ResourceNotFoundException {
        public TransactionChannelNotFoundException(String channel) {
            super("Channel [" + channel + "] not found.");
        }
    }

    public static class AccountNotFoundException extends ResourceNotFoundException {
        public AccountNotFoundException(String accountNumber) {
            super("AccountNumber [" + accountNumber + "] not found.");
        }
    }
}