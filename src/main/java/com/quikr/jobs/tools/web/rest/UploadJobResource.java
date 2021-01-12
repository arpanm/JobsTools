package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.service.UploadJobService;
import com.quikr.jobs.tools.web.rest.errors.BadRequestAlertException;
import com.quikr.jobs.tools.service.dto.UploadJobDTO;

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
 * REST controller for managing {@link com.quikr.jobs.tools.domain.UploadJob}.
 */
@RestController
@RequestMapping("/api")
public class UploadJobResource {

    private final Logger log = LoggerFactory.getLogger(UploadJobResource.class);

    private static final String ENTITY_NAME = "uploadJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UploadJobService uploadJobService;

    public UploadJobResource(UploadJobService uploadJobService) {
        this.uploadJobService = uploadJobService;
    }

    /**
     * {@code POST  /upload-jobs} : Create a new uploadJob.
     *
     * @param uploadJobDTO the uploadJobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uploadJobDTO, or with status {@code 400 (Bad Request)} if the uploadJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/upload-jobs")
    public ResponseEntity<UploadJobDTO> createUploadJob(@RequestBody UploadJobDTO uploadJobDTO) throws URISyntaxException {
        log.debug("REST request to save UploadJob : {}", uploadJobDTO);
        if (uploadJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new uploadJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UploadJobDTO result = uploadJobService.save(uploadJobDTO);
        return ResponseEntity.created(new URI("/api/upload-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /upload-jobs} : Updates an existing uploadJob.
     *
     * @param uploadJobDTO the uploadJobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uploadJobDTO,
     * or with status {@code 400 (Bad Request)} if the uploadJobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uploadJobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/upload-jobs")
    public ResponseEntity<UploadJobDTO> updateUploadJob(@RequestBody UploadJobDTO uploadJobDTO) throws URISyntaxException {
        log.debug("REST request to update UploadJob : {}", uploadJobDTO);
        if (uploadJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UploadJobDTO result = uploadJobService.save(uploadJobDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uploadJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /upload-jobs} : get all the uploadJobs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uploadJobs in body.
     */
    @GetMapping("/upload-jobs")
    public ResponseEntity<List<UploadJobDTO>> getAllUploadJobs(Pageable pageable) {
        log.debug("REST request to get a page of UploadJobs");
        Page<UploadJobDTO> page = uploadJobService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /upload-jobs/:id} : get the "id" uploadJob.
     *
     * @param id the id of the uploadJobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uploadJobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/upload-jobs/{id}")
    public ResponseEntity<UploadJobDTO> getUploadJob(@PathVariable Long id) {
        log.debug("REST request to get UploadJob : {}", id);
        Optional<UploadJobDTO> uploadJobDTO = uploadJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uploadJobDTO);
    }

    /**
     * {@code DELETE  /upload-jobs/:id} : delete the "id" uploadJob.
     *
     * @param id the id of the uploadJobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/upload-jobs/{id}")
    public ResponseEntity<Void> deleteUploadJob(@PathVariable Long id) {
        log.debug("REST request to delete UploadJob : {}", id);
        uploadJobService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
