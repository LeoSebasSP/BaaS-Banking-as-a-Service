package com.lsp.baas.Util;

import com.lsp.baas.Exception.ResourceNotFoundException;
import com.lsp.baas.Persistence.Entity.AccountType;
import com.lsp.baas.Persistence.Entity.Currency;
import com.lsp.baas.Persistence.Entity.TransactionChannel;
import com.lsp.baas.Persistence.Repository.ITransactionChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountNumberGenerator {

    private final ITransactionChannelRepository iTransactionChannelRepository;

    public String generateAccounNumber(
            AccountType accountType,
            Currency currency,
            Long currentSequenceAccountNumber
    ){
        TransactionChannel transactionChannel = iTransactionChannelRepository.findByName("BAAS").
                orElseThrow(() -> new ResourceNotFoundException.TransactionChannelNotFoundException("BAAS"));
        String channel = transactionChannel.getId().toString();
        String accountTypeCode = accountType.getId().toString();
        String currencyCode = currency.getName().equalsIgnoreCase("PEN") ? "0": "1";
        String correlative = accountType.getId() == 10 || accountType.getId() == 11 ?
                String.format("%06d", currentSequenceAccountNumber) :
                String.format("%07d", currentSequenceAccountNumber);
        String rootAccount = channel + accountTypeCode + currencyCode + correlative;
        String checkDigit = Integer.toString(this.calculateCheckDigit(rootAccount));

        return rootAccount + checkDigit;
    }



    /**
     * Luhn's algorithm
     */
    private int calculateCheckDigit(String rootNumber) {
        int sum = 0;
        // This flag determines whether we multiply by 2 or 1.
        // Since we start from the right, the first digit of the root is ALWAYS multiplied by 2.
        boolean multiplyByTwo = true;

        // We traverse the String from the last character (length - 1) to the first (0)
        for (int i = rootNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(rootNumber.charAt(i));

            if (multiplyByTwo) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9; // If it is greater than 9, we add its digits (ex: 12 -> 1+2 = 3 or 12-9 = 3)
                }
            }

            sum += digit;

            // We toggle the flag for the next digit
            multiplyByTwo = !multiplyByTwo;
        }

        // We apply the mathematical formula to find how much is left for the next multiple of 10
        return (10 - (sum % 10)) % 10;
    }
}
