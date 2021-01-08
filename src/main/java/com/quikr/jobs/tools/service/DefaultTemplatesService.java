package com.quikr.jobs.tools.service;

import com.quikr.jobs.tools.service.dto.DefaultTemplatesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.quikr.jobs.tools.domain.DefaultTemplates}.
 */
public interface DefaultTemplatesService {

    /**
     * Save a defaultTemplates.
     *
     * @param defaultTemplatesDTO the entity to save.
     * @return the persisted entity.
     */
    DefaultTemplatesDTO save(DefaultTemplatesDTO defaultTemplatesDTO);

    /**
     * Get all the defaultTemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DefaultTemplatesDTO> findAll(Pageable pageable);

    /**
     * Get all the defaultTemplates with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<DefaultTemplatesDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" defaultTemplates.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DefaultTemplatesDTO> findOne(Long id);

    /**
     * Delete the "id" defaultTemplates.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
