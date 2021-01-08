package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.service.DefaultTemplatesFieldsService;
import com.quikr.jobs.tools.web.rest.errors.BadRequestAlertException;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesFieldsDTO;

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
 * REST controller for managing {@link com.quikr.jobs.tools.domain.DefaultTemplatesFields}.
 */
@RestController
@RequestMapping("/api")
public class DefaultTemplatesFieldsResource {

    private final Logger log = LoggerFactory.getLogger(DefaultTemplatesFieldsResource.class);

    private static final String ENTITY_NAME = "defaultTemplatesFields";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefaultTemplatesFieldsService defaultTemplatesFieldsService;

    public DefaultTemplatesFieldsResource(DefaultTemplatesFieldsService defaultTemplatesFieldsService) {
        this.defaultTemplatesFieldsService = defaultTemplatesFieldsService;
    }

    /**
     * {@code POST  /default-templates-fields} : Create a new defaultTemplatesFields.
     *
     * @param defaultTemplatesFieldsDTO the defaultTemplatesFieldsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defaultTemplatesFieldsDTO, or with status {@code 400 (Bad Request)} if the defaultTemplatesFields has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/default-templates-fields")
    public ResponseEntity<DefaultTemplatesFieldsDTO> createDefaultTemplatesFields(@Valid @RequestBody DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO) throws URISyntaxException {
        log.debug("REST request to save DefaultTemplatesFields : {}", defaultTemplatesFieldsDTO);
        if (defaultTemplatesFieldsDTO.getId() != null) {
            throw new BadRequestAlertException("A new defaultTemplatesFields cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DefaultTemplatesFieldsDTO result = defaultTemplatesFieldsService.save(defaultTemplatesFieldsDTO);
        return ResponseEntity.created(new URI("/api/default-templates-fields/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /default-templates-fields} : Updates an existing defaultTemplatesFields.
     *
     * @param defaultTemplatesFieldsDTO the defaultTemplatesFieldsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defaultTemplatesFieldsDTO,
     * or with status {@code 400 (Bad Request)} if the defaultTemplatesFieldsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defaultTemplatesFieldsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/default-templates-fields")
    public ResponseEntity<DefaultTemplatesFieldsDTO> updateDefaultTemplatesFields(@Valid @RequestBody DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO) throws URISyntaxException {
        log.debug("REST request to update DefaultTemplatesFields : {}", defaultTemplatesFieldsDTO);
        if (defaultTemplatesFieldsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DefaultTemplatesFieldsDTO result = defaultTemplatesFieldsService.save(defaultTemplatesFieldsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, defaultTemplatesFieldsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /default-templates-fields} : get all the defaultTemplatesFields.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defaultTemplatesFields in body.
     */
    @GetMapping("/default-templates-fields")
    public ResponseEntity<List<DefaultTemplatesFieldsDTO>> getAllDefaultTemplatesFields(Pageable pageable) {
        log.debug("REST request to get a page of DefaultTemplatesFields");
        Page<DefaultTemplatesFieldsDTO> page = defaultTemplatesFieldsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /default-templates-fields/:id} : get the "id" defaultTemplatesFields.
     *
     * @param id the id of the defaultTemplatesFieldsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defaultTemplatesFieldsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/default-templates-fields/{id}")
    public ResponseEntity<DefaultTemplatesFieldsDTO> getDefaultTemplatesFields(@PathVariable Long id) {
        log.debug("REST request to get DefaultTemplatesFields : {}", id);
        Optional<DefaultTemplatesFieldsDTO> defaultTemplatesFieldsDTO = defaultTemplatesFieldsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(defaultTemplatesFieldsDTO);
    }

    /**
     * {@code DELETE  /default-templates-fields/:id} : delete the "id" defaultTemplatesFields.
     *
     * @param id the id of the defaultTemplatesFieldsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/default-templates-fields/{id}")
    public ResponseEntity<Void> deleteDefaultTemplatesFields(@PathVariable Long id) {
        log.debug("REST request to delete DefaultTemplatesFields : {}", id);
        defaultTemplatesFieldsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
