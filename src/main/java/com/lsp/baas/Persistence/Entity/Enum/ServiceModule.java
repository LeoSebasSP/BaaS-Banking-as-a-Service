package com.lsp.baas.Persistence.Entity.Enum;

public enum ServiceModule {
    PAYMENTS, // Module for making transfers, for customers use only.
    MOVEMENTS, // Module for list an account's movements, for customers use only.
    ACCOUNTS,  // Module for list customer's accounts, for customers use only.
}
