package com.quikr.jobs.tools.repository;

import com.quikr.jobs.tools.domain.DefaultTemplates;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DefaultTemplates entity.
 */
@Repository
public interface DefaultTemplatesRepository extends JpaRepository<DefaultTemplates, Long> {

    @Query(value = "select distinct defaultTemplates from DefaultTemplates defaultTemplates left join fetch defaultTemplates.defaultTemplatesFields",
        countQuery = "select count(distinct defaultTemplates) from DefaultTemplates defaultTemplates")
    Page<DefaultTemplates> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct defaultTemplates from DefaultTemplates defaultTemplates left join fetch defaultTemplates.defaultTemplatesFields")
    List<DefaultTemplates> findAllWithEagerRelationships();

    @Query("select defaultTemplates from DefaultTemplates defaultTemplates left join fetch defaultTemplates.defaultTemplatesFields where defaultTemplates.id =:id")
    Optional<DefaultTemplates> findOneWithEagerRelationships(@Param("id") Long id);
}
