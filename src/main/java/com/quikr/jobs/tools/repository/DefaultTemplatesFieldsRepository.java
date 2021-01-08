package com.quikr.jobs.tools.repository;

import com.quikr.jobs.tools.domain.DefaultTemplatesFields;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DefaultTemplatesFields entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefaultTemplatesFieldsRepository extends JpaRepository<DefaultTemplatesFields, Long> {
}
