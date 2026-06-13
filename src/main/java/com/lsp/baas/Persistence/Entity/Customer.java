package com.lsp.baas.Persistence.Entity;

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
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_customers_document_per_type",
                        columnNames = {"document_type_id", "doc_number"}
                )
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 300, nullable = false)
    private String fullName;

    @Column(name = "company_name", length = 300, nullable = false)
    private String companyName;

    @Column(name = "doc_number", length = 20, nullable = false)
    private String docNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_type_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_customers_documentTypes",
                    options = "ON DELETE RESTRICT"
            )
    )
    private DocumentType documentType;

    @Column(name = "cuid", length = 30, nullable = false, unique = true)
    private String cuid; // CUID: Customer Unique Identifier

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

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
