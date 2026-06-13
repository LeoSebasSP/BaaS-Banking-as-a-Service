package com.lsp.baas.Persistence.Entity;

import com.lsp.baas.Persistence.Entity.Enum.Permission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Immutable
@NoArgsConstructor
@Table(name = "roles")
public class Rol {

    @Id
    private Integer id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_rol_permission",
                    columnNames = {"rol_id", "permission"}
            )
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    private Set<Permission> permissions = new HashSet<>();
}
