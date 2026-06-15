package com.lsp.baas.Persistence.Entity;

import com.lsp.baas.Persistence.Entity.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "transactions",
        check = @CheckConstraint(
                name = "chk_different_accounts",
                constraint = "source_account <> destination_account"
        )
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_code", length = 50, nullable = false, unique = true)
    private String paymentCode;

    @Column(name = "operation_number", precision = 4, nullable = false)
    private BigDecimal operationNumber;

    @Column(name = "source_account", nullable = false, length = 20)
    private String sourceAccount;

    @Column(name = "destination_account", nullable = false, length = 20)
    private String destinationAccount;

    @Column(
            name = "amount",
            precision = 15,
            scale = 4,
            nullable = false,
            check = @CheckConstraint(
                    name = "chk_positive_amount",
                    constraint = "amount > 0"
            )
    )
    private BigDecimal amount;


    @Column(name = "fee_amount", precision = 15, scale = 4, nullable = false)
    private BigDecimal feeAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "currency_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_transactions_currencies",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "transaction_type_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_transactions_transactionTypes",
                    options = "ON DELETE RESTRICT"
            )
    )
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "transaction_channel_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_transactions_transactionChannels",
                    options = "ON DELETE RESTRICT"
            )
    )
    private TransactionChannel transactionChannel;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "device_id", length = 100)
    private String deviceId;

    @Column(name = "external_provider_id", length = 100)
    private String externalProviderId;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
