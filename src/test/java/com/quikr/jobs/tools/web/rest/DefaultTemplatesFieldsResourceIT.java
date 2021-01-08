package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.JobsOpsToolApp;
import com.quikr.jobs.tools.domain.DefaultTemplatesFields;
import com.quikr.jobs.tools.repository.DefaultTemplatesFieldsRepository;
import com.quikr.jobs.tools.service.DefaultTemplatesFieldsService;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesFieldsDTO;
import com.quikr.jobs.tools.service.mapper.DefaultTemplatesFieldsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.quikr.jobs.tools.domain.enumeration.DefaultFieldType;
/**
 * Integration tests for the {@link DefaultTemplatesFieldsResource} REST controller.
 */
@SpringBootTest(classes = JobsOpsToolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DefaultTemplatesFieldsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final DefaultFieldType DEFAULT_TYPE = DefaultFieldType.STR;
    private static final DefaultFieldType UPDATED_TYPE = DefaultFieldType.NUM;

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    @Autowired
    private DefaultTemplatesFieldsRepository defaultTemplatesFieldsRepository;

    @Autowired
    private DefaultTemplatesFieldsMapper defaultTemplatesFieldsMapper;

    @Autowired
    private DefaultTemplatesFieldsService defaultTemplatesFieldsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDefaultTemplatesFieldsMockMvc;

    private DefaultTemplatesFields defaultTemplatesFields;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultTemplatesFields createEntity(EntityManager em) {
        DefaultTemplatesFields defaultTemplatesFields = new DefaultTemplatesFields()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .displayName(DEFAULT_DISPLAY_NAME);
        return defaultTemplatesFields;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultTemplatesFields createUpdatedEntity(EntityManager em) {
        DefaultTemplatesFields defaultTemplatesFields = new DefaultTemplatesFields()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .displayName(UPDATED_DISPLAY_NAME);
        return defaultTemplatesFields;
    }

    @BeforeEach
    public void initTest() {
        defaultTemplatesFields = createEntity(em);
    }

    @Test
    @Transactional
    public void createDefaultTemplatesFields() throws Exception {
        int databaseSizeBeforeCreate = defaultTemplatesFieldsRepository.findAll().size();
        // Create the DefaultTemplatesFields
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);
        restDefaultTemplatesFieldsMockMvc.perform(post("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isCreated());

        // Validate the DefaultTemplatesFields in the database
        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeCreate + 1);
        DefaultTemplatesFields testDefaultTemplatesFields = defaultTemplatesFieldsList.get(defaultTemplatesFieldsList.size() - 1);
        assertThat(testDefaultTemplatesFields.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDefaultTemplatesFields.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDefaultTemplatesFields.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void createDefaultTemplatesFieldsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = defaultTemplatesFieldsRepository.findAll().size();

        // Create the DefaultTemplatesFields with an existing ID
        defaultTemplatesFields.setId(1L);
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDefaultTemplatesFieldsMockMvc.perform(post("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DefaultTemplatesFields in the database
        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = defaultTemplatesFieldsRepository.findAll().size();
        // set the field null
        defaultTemplatesFields.setName(null);

        // Create the DefaultTemplatesFields, which fails.
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);


        restDefaultTemplatesFieldsMockMvc.perform(post("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isBadRequest());

        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = defaultTemplatesFieldsRepository.findAll().size();
        // set the field null
        defaultTemplatesFields.setType(null);

        // Create the DefaultTemplatesFields, which fails.
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);


        restDefaultTemplatesFieldsMockMvc.perform(post("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isBadRequest());

        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDefaultTemplatesFields() throws Exception {
        // Initialize the database
        defaultTemplatesFieldsRepository.saveAndFlush(defaultTemplatesFields);

        // Get all the defaultTemplatesFieldsList
        restDefaultTemplatesFieldsMockMvc.perform(get("/api/default-templates-fields?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(defaultTemplatesFields.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)));
    }
    
    @Test
    @Transactional
    public void getDefaultTemplatesFields() throws Exception {
        // Initialize the database
        defaultTemplatesFieldsRepository.saveAndFlush(defaultTemplatesFields);

        // Get the defaultTemplatesFields
        restDefaultTemplatesFieldsMockMvc.perform(get("/api/default-templates-fields/{id}", defaultTemplatesFields.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(defaultTemplatesFields.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingDefaultTemplatesFields() throws Exception {
        // Get the defaultTemplatesFields
        restDefaultTemplatesFieldsMockMvc.perform(get("/api/default-templates-fields/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDefaultTemplatesFields() throws Exception {
        // Initialize the database
        defaultTemplatesFieldsRepository.saveAndFlush(defaultTemplatesFields);

        int databaseSizeBeforeUpdate = defaultTemplatesFieldsRepository.findAll().size();

        // Update the defaultTemplatesFields
        DefaultTemplatesFields updatedDefaultTemplatesFields = defaultTemplatesFieldsRepository.findById(defaultTemplatesFields.getId()).get();
        // Disconnect from session so that the updates on updatedDefaultTemplatesFields are not directly saved in db
        em.detach(updatedDefaultTemplatesFields);
        updatedDefaultTemplatesFields
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .displayName(UPDATED_DISPLAY_NAME);
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(updatedDefaultTemplatesFields);

        restDefaultTemplatesFieldsMockMvc.perform(put("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isOk());

        // Validate the DefaultTemplatesFields in the database
        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeUpdate);
        DefaultTemplatesFields testDefaultTemplatesFields = defaultTemplatesFieldsList.get(defaultTemplatesFieldsList.size() - 1);
        assertThat(testDefaultTemplatesFields.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDefaultTemplatesFields.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDefaultTemplatesFields.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDefaultTemplatesFields() throws Exception {
        int databaseSizeBeforeUpdate = defaultTemplatesFieldsRepository.findAll().size();

        // Create the DefaultTemplatesFields
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO = defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDefaultTemplatesFieldsMockMvc.perform(put("/api/default-templates-fields")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesFieldsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DefaultTemplatesFields in the database
        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDefaultTemplatesFields() throws Exception {
        // Initialize the database
        defaultTemplatesFieldsRepository.saveAndFlush(defaultTemplatesFields);

        int databaseSizeBeforeDelete = defaultTemplatesFieldsRepository.findAll().size();

        // Delete the defaultTemplatesFields
        restDefaultTemplatesFieldsMockMvc.perform(delete("/api/default-templates-fields/{id}", defaultTemplatesFields.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DefaultTemplatesFields> defaultTemplatesFieldsList = defaultTemplatesFieldsRepository.findAll();
        assertThat(defaultTemplatesFieldsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
