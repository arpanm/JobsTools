package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.service.DefaultTemplatesService;
import com.quikr.jobs.tools.web.rest.errors.BadRequestAlertException;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.quikr.jobs.tools.domain.DefaultTemplates}.
 */
@RestController
@RequestMapping("/api")
public class DefaultTemplatesResource {

    private final Logger log = LoggerFactory.getLogger(DefaultTemplatesResource.class);

    private static final String ENTITY_NAME = "defaultTemplates";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefaultTemplatesService defaultTemplatesService;

    public DefaultTemplatesResource(DefaultTemplatesService defaultTemplatesService) {
        this.defaultTemplatesService = defaultTemplatesService;
    }

    /**
     * {@code POST  /default-templates} : Create a new defaultTemplates.
     *
     * @param defaultTemplatesDTO the defaultTemplatesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defaultTemplatesDTO, or with status {@code 400 (Bad Request)} if the defaultTemplates has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/default-templates")
    public ResponseEntity<DefaultTemplatesDTO> createDefaultTemplates(@Valid @RequestBody DefaultTemplatesDTO defaultTemplatesDTO) throws URISyntaxException {
        log.debug("REST request to save DefaultTemplates : {}", defaultTemplatesDTO);
        if (defaultTemplatesDTO.getId() != null) {
            throw new BadRequestAlertException("A new defaultTemplates cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DefaultTemplatesDTO result = defaultTemplatesService.save(defaultTemplatesDTO);
        return ResponseEntity.created(new URI("/api/default-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /default-templates} : Updates an existing defaultTemplates.
     *
     * @param defaultTemplatesDTO the defaultTemplatesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defaultTemplatesDTO,
     * or with status {@code 400 (Bad Request)} if the defaultTemplatesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defaultTemplatesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/default-templates")
    public ResponseEntity<DefaultTemplatesDTO> updateDefaultTemplates(@Valid @RequestBody DefaultTemplatesDTO defaultTemplatesDTO) throws URISyntaxException {
        log.debug("REST request to update DefaultTemplates : {}", defaultTemplatesDTO);
        if (defaultTemplatesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DefaultTemplatesDTO result = defaultTemplatesService.save(defaultTemplatesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, defaultTemplatesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /default-templates} : get all the defaultTemplates.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defaultTemplates in body.
     */
    @GetMapping("/default-templates")
    public ResponseEntity<List<DefaultTemplatesDTO>> getAllDefaultTemplates(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of DefaultTemplates");
        Page<DefaultTemplatesDTO> page;
        if (eagerload) {
            page = defaultTemplatesService.findAllWithEagerRelationships(pageable);
        } else {
            page = defaultTemplatesService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /default-templates/:id} : get the "id" defaultTemplates.
     *
     * @param id the id of the defaultTemplatesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defaultTemplatesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/default-templates/{id}")
    public ResponseEntity<DefaultTemplatesDTO> getDefaultTemplates(@PathVariable Long id) {
        log.debug("REST request to get DefaultTemplates : {}", id);
        Optional<DefaultTemplatesDTO> defaultTemplatesDTO = defaultTemplatesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(defaultTemplatesDTO);
    }

    /**
     * {@code DELETE  /default-templates/:id} : delete the "id" defaultTemplates.
     *
     * @param id the id of the defaultTemplatesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/default-templates/{id}")
    public ResponseEntity<Void> deleteDefaultTemplates(@PathVariable Long id) {
        log.debug("REST request to delete DefaultTemplates : {}", id);
        defaultTemplatesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
