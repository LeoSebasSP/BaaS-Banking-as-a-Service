package com.lsp.baas.Persistence.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T,I> extends JpaRepository<T,I> {
}
