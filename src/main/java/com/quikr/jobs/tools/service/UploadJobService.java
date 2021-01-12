package com.quikr.jobs.tools.service;

import com.quikr.jobs.tools.service.dto.UploadJobDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.quikr.jobs.tools.domain.UploadJob}.
 */
public interface UploadJobService {

    /**
     * Save a uploadJob.
     *
     * @param uploadJobDTO the entity to save.
     * @return the persisted entity.
     */
    UploadJobDTO save(UploadJobDTO uploadJobDTO);

    /**
     * Get all the uploadJobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UploadJobDTO> findAll(Pageable pageable);


    /**
     * Get the "id" uploadJob.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UploadJobDTO> findOne(Long id);

    /**
     * Delete the "id" uploadJob.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
