package com.lsp.baas.Persistence.Entity;

import com.lsp.baas.Persistence.Entity.Enum.AccountStatus;
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
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_accounts_customers",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Customer customer;

    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "account_type_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_accounts_accountTypes",
                    options = "ON DELETE RESTRICT"
            )
    )
    private AccountType accountType;

    @Column(
            name = "balance",
            precision = 15,
            scale = 4,
            nullable = false,
            check = @CheckConstraint(
                    name = "chk_positive_balance",
                    constraint = "balance >= 0"
            )
    )
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "currency_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_accounts_currencies",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private AccountStatus status;

    @Column(name = "creator_user", nullable = false)
    private Long creatorUser;

    @Column(name = "updater_user")
    private Long updaterUser;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
