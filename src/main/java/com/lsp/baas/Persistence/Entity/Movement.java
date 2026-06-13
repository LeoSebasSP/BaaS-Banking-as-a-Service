package com.lsp.baas.Persistence.Entity;

import com.lsp.baas.Persistence.Entity.Enum.MovementType;
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
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movement_code", length = 100, nullable = false, unique = true)
    private String movementCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "transaction_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_movements_transactions",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "account_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_movements_accounts",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MovementType type;

    @Column(
            name = "amount",
            precision = 15,
            scale = 4,
            nullable = false,
            check = @CheckConstraint(
                    name = "chk_positive_mv_amount",
                    constraint = "amount > 0"
            )
    )
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "currency_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_movements_currencies",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Currency currency;

    @Column(name = "balance_after", precision = 15, scale = 4, nullable = false)
    private BigDecimal balanceAfter;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
