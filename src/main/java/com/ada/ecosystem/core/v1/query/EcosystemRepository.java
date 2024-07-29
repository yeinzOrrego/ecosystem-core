package com.ada.ecosystem.core.v1.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EcosystemRepository<T, I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {}