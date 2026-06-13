package com.lsp.baas.Persistence.Entity;

import com.lsp.baas.Persistence.Entity.Enum.ServiceModule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "affiliations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_affiliations_customers_services",
                        columnNames = {"customer_id", "service"}
                )
        }
)
public class Affiliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_affiliations_customers",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Customer customer;

    @Column(name = "client_id", length = 100, nullable = false, unique = true)
    private String clientId;

    @Column(name = "client_secret", length = 100, nullable = false, unique = true)
    private String clientSecret;

    @Enumerated(EnumType.STRING)
    @Column(name = "service", nullable = false)
    private ServiceModule serviceModule;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Column(name = "creator_user", nullable = false)
    private Long creatorUser;

    @Column(name = "updater_user")
    private Long updaterUser;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
