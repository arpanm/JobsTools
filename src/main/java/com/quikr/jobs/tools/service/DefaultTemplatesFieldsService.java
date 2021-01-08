package com.quikr.jobs.tools.service;

import com.quikr.jobs.tools.service.dto.DefaultTemplatesFieldsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.quikr.jobs.tools.domain.DefaultTemplatesFields}.
 */
public interface DefaultTemplatesFieldsService {

    /**
     * Save a defaultTemplatesFields.
     *
     * @param defaultTemplatesFieldsDTO the entity to save.
     * @return the persisted entity.
     */
    DefaultTemplatesFieldsDTO save(DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO);

    /**
     * Get all the defaultTemplatesFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DefaultTemplatesFieldsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" defaultTemplatesFields.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DefaultTemplatesFieldsDTO> findOne(Long id);

    /**
     * Delete the "id" defaultTemplatesFields.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
