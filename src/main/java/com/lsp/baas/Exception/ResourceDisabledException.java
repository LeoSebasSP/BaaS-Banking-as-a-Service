package com.lsp.baas.Exception;

public class ResourceDisabledException extends RuntimeException {

    public ResourceDisabledException(String s) {
        super(s);
    }

    public static class CustomerDisabledException extends ResourceDisabledException {
        public CustomerDisabledException(String cuid) {
            super("Customer with CUID [" + cuid + "] is disabled.");
        }
    }
}