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
        name = "bank_users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_bankUsers_document_per_type",
                        columnNames = {"document_type_id", "doc_number"}
                )
        }
)
public class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "doc_number", length = 20, nullable = false)
    private String docNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "document_type_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bankUsers_documentTypes",
                    options = "ON DELETE RESTRICT"
            )
    )
    private DocumentType documentType;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "rol_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_bankUsers_roles",
                    options = "ON DELETE RESTRICT"
            )
    )
    private Rol rol;

    @Column(name = "creator_user", nullable = false)
    private Long creatorUser;

    @Column(name = "updater_user")
    private Long updaterUser;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
