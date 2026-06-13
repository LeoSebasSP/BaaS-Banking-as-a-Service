package com.lsp.baas.Persistence.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@Immutable
@NoArgsConstructor
@Table(name = "transaction_types")
public class TransactionType {

    @Id
    private Integer id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
}
