package com.quikr.jobs.tools.service;

import com.quikr.jobs.tools.service.dto.QCashUploadRowDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.quikr.jobs.tools.domain.QCashUploadRow}.
 */
public interface QCashUploadRowService {

    /**
     * Save a qCashUploadRow.
     *
     * @param qCashUploadRowDTO the entity to save.
     * @return the persisted entity.
     */
    QCashUploadRowDTO save(QCashUploadRowDTO qCashUploadRowDTO);

    /**
     * Get all the qCashUploadRows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QCashUploadRowDTO> findAll(Pageable pageable);


    /**
     * Get the "id" qCashUploadRow.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QCashUploadRowDTO> findOne(Long id);

    /**
     * Delete the "id" qCashUploadRow.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
