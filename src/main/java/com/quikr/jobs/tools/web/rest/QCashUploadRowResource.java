package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.service.QCashUploadRowService;
import com.quikr.jobs.tools.web.rest.errors.BadRequestAlertException;
import com.quikr.jobs.tools.service.dto.QCashUploadRowDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.quikr.jobs.tools.domain.QCashUploadRow}.
 */
@RestController
@RequestMapping("/api")
public class QCashUploadRowResource {

    private final Logger log = LoggerFactory.getLogger(QCashUploadRowResource.class);

    private static final String ENTITY_NAME = "qCashUploadRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QCashUploadRowService qCashUploadRowService;

    public QCashUploadRowResource(QCashUploadRowService qCashUploadRowService) {
        this.qCashUploadRowService = qCashUploadRowService;
    }

    /**
     * {@code POST  /q-cash-upload-rows} : Create a new qCashUploadRow.
     *
     * @param qCashUploadRowDTO the qCashUploadRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qCashUploadRowDTO, or with status {@code 400 (Bad Request)} if the qCashUploadRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/q-cash-upload-rows")
    public ResponseEntity<QCashUploadRowDTO> createQCashUploadRow(@RequestBody QCashUploadRowDTO qCashUploadRowDTO) throws URISyntaxException {
        log.debug("REST request to save QCashUploadRow : {}", qCashUploadRowDTO);
        if (qCashUploadRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new qCashUploadRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QCashUploadRowDTO result = qCashUploadRowService.save(qCashUploadRowDTO);
        return ResponseEntity.created(new URI("/api/q-cash-upload-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /q-cash-upload-rows} : Updates an existing qCashUploadRow.
     *
     * @param qCashUploadRowDTO the qCashUploadRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qCashUploadRowDTO,
     * or with status {@code 400 (Bad Request)} if the qCashUploadRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qCashUploadRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/q-cash-upload-rows")
    public ResponseEntity<QCashUploadRowDTO> updateQCashUploadRow(@RequestBody QCashUploadRowDTO qCashUploadRowDTO) throws URISyntaxException {
        log.debug("REST request to update QCashUploadRow : {}", qCashUploadRowDTO);
        if (qCashUploadRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QCashUploadRowDTO result = qCashUploadRowService.save(qCashUploadRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, qCashUploadRowDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /q-cash-upload-rows} : get all the qCashUploadRows.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qCashUploadRows in body.
     */
    @GetMapping("/q-cash-upload-rows")
    public ResponseEntity<List<QCashUploadRowDTO>> getAllQCashUploadRows(Pageable pageable) {
        log.debug("REST request to get a page of QCashUploadRows");
        Page<QCashUploadRowDTO> page = qCashUploadRowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /q-cash-upload-rows/:id} : get the "id" qCashUploadRow.
     *
     * @param id the id of the qCashUploadRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qCashUploadRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/q-cash-upload-rows/{id}")
    public ResponseEntity<QCashUploadRowDTO> getQCashUploadRow(@PathVariable Long id) {
        log.debug("REST request to get QCashUploadRow : {}", id);
        Optional<QCashUploadRowDTO> qCashUploadRowDTO = qCashUploadRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qCashUploadRowDTO);
    }

    /**
     * {@code DELETE  /q-cash-upload-rows/:id} : delete the "id" qCashUploadRow.
     *
     * @param id the id of the qCashUploadRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/q-cash-upload-rows/{id}")
    public ResponseEntity<Void> deleteQCashUploadRow(@PathVariable Long id) {
        log.debug("REST request to delete QCashUploadRow : {}", id);
        qCashUploadRowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
