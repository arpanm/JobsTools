package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.JobsOpsToolApp;
import com.quikr.jobs.tools.domain.DefaultTemplates;
import com.quikr.jobs.tools.repository.DefaultTemplatesRepository;
import com.quikr.jobs.tools.service.DefaultTemplatesService;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesDTO;
import com.quikr.jobs.tools.service.mapper.DefaultTemplatesMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DefaultTemplatesResource} REST controller.
 */
@SpringBootTest(classes = JobsOpsToolApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DefaultTemplatesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_STYLE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVED_TEMPLATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_APPROVED_TEMPLATE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DefaultTemplatesRepository defaultTemplatesRepository;

    @Mock
    private DefaultTemplatesRepository defaultTemplatesRepositoryMock;

    @Autowired
    private DefaultTemplatesMapper defaultTemplatesMapper;

    @Mock
    private DefaultTemplatesService defaultTemplatesServiceMock;

    @Autowired
    private DefaultTemplatesService defaultTemplatesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDefaultTemplatesMockMvc;

    private DefaultTemplates defaultTemplates;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultTemplates createEntity(EntityManager em) {
        DefaultTemplates defaultTemplates = new DefaultTemplates()
            .name(DEFAULT_NAME)
            .contentStyle(DEFAULT_CONTENT_STYLE)
            .comments(DEFAULT_COMMENTS)
            .approvedTemplateId(DEFAULT_APPROVED_TEMPLATE_ID)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return defaultTemplates;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DefaultTemplates createUpdatedEntity(EntityManager em) {
        DefaultTemplates defaultTemplates = new DefaultTemplates()
            .name(UPDATED_NAME)
            .contentStyle(UPDATED_CONTENT_STYLE)
            .comments(UPDATED_COMMENTS)
            .approvedTemplateId(UPDATED_APPROVED_TEMPLATE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return defaultTemplates;
    }

    @BeforeEach
    public void initTest() {
        defaultTemplates = createEntity(em);
    }

    @Test
    @Transactional
    public void createDefaultTemplates() throws Exception {
        int databaseSizeBeforeCreate = defaultTemplatesRepository.findAll().size();
        // Create the DefaultTemplates
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);
        restDefaultTemplatesMockMvc.perform(post("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isCreated());

        // Validate the DefaultTemplates in the database
        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeCreate + 1);
        DefaultTemplates testDefaultTemplates = defaultTemplatesList.get(defaultTemplatesList.size() - 1);
        assertThat(testDefaultTemplates.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDefaultTemplates.getContentStyle()).isEqualTo(DEFAULT_CONTENT_STYLE);
        assertThat(testDefaultTemplates.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testDefaultTemplates.getApprovedTemplateId()).isEqualTo(DEFAULT_APPROVED_TEMPLATE_ID);
        assertThat(testDefaultTemplates.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testDefaultTemplates.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testDefaultTemplates.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testDefaultTemplates.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createDefaultTemplatesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = defaultTemplatesRepository.findAll().size();

        // Create the DefaultTemplates with an existing ID
        defaultTemplates.setId(1L);
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDefaultTemplatesMockMvc.perform(post("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DefaultTemplates in the database
        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = defaultTemplatesRepository.findAll().size();
        // set the field null
        defaultTemplates.setName(null);

        // Create the DefaultTemplates, which fails.
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);


        restDefaultTemplatesMockMvc.perform(post("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isBadRequest());

        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContentStyleIsRequired() throws Exception {
        int databaseSizeBeforeTest = defaultTemplatesRepository.findAll().size();
        // set the field null
        defaultTemplates.setContentStyle(null);

        // Create the DefaultTemplates, which fails.
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);


        restDefaultTemplatesMockMvc.perform(post("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isBadRequest());

        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApprovedTemplateIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = defaultTemplatesRepository.findAll().size();
        // set the field null
        defaultTemplates.setApprovedTemplateId(null);

        // Create the DefaultTemplates, which fails.
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);


        restDefaultTemplatesMockMvc.perform(post("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isBadRequest());

        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDefaultTemplates() throws Exception {
        // Initialize the database
        defaultTemplatesRepository.saveAndFlush(defaultTemplates);

        // Get all the defaultTemplatesList
        restDefaultTemplatesMockMvc.perform(get("/api/default-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(defaultTemplates.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].contentStyle").value(hasItem(DEFAULT_CONTENT_STYLE)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].approvedTemplateId").value(hasItem(DEFAULT_APPROVED_TEMPLATE_ID)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDefaultTemplatesWithEagerRelationshipsIsEnabled() throws Exception {
        when(defaultTemplatesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDefaultTemplatesMockMvc.perform(get("/api/default-templates?eagerload=true"))
            .andExpect(status().isOk());

        verify(defaultTemplatesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDefaultTemplatesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(defaultTemplatesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDefaultTemplatesMockMvc.perform(get("/api/default-templates?eagerload=true"))
            .andExpect(status().isOk());

        verify(defaultTemplatesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDefaultTemplates() throws Exception {
        // Initialize the database
        defaultTemplatesRepository.saveAndFlush(defaultTemplates);

        // Get the defaultTemplates
        restDefaultTemplatesMockMvc.perform(get("/api/default-templates/{id}", defaultTemplates.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(defaultTemplates.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.contentStyle").value(DEFAULT_CONTENT_STYLE))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.approvedTemplateId").value(DEFAULT_APPROVED_TEMPLATE_ID))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDefaultTemplates() throws Exception {
        // Get the defaultTemplates
        restDefaultTemplatesMockMvc.perform(get("/api/default-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDefaultTemplates() throws Exception {
        // Initialize the database
        defaultTemplatesRepository.saveAndFlush(defaultTemplates);

        int databaseSizeBeforeUpdate = defaultTemplatesRepository.findAll().size();

        // Update the defaultTemplates
        DefaultTemplates updatedDefaultTemplates = defaultTemplatesRepository.findById(defaultTemplates.getId()).get();
        // Disconnect from session so that the updates on updatedDefaultTemplates are not directly saved in db
        em.detach(updatedDefaultTemplates);
        updatedDefaultTemplates
            .name(UPDATED_NAME)
            .contentStyle(UPDATED_CONTENT_STYLE)
            .comments(UPDATED_COMMENTS)
            .approvedTemplateId(UPDATED_APPROVED_TEMPLATE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(updatedDefaultTemplates);

        restDefaultTemplatesMockMvc.perform(put("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isOk());

        // Validate the DefaultTemplates in the database
        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeUpdate);
        DefaultTemplates testDefaultTemplates = defaultTemplatesList.get(defaultTemplatesList.size() - 1);
        assertThat(testDefaultTemplates.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDefaultTemplates.getContentStyle()).isEqualTo(UPDATED_CONTENT_STYLE);
        assertThat(testDefaultTemplates.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testDefaultTemplates.getApprovedTemplateId()).isEqualTo(UPDATED_APPROVED_TEMPLATE_ID);
        assertThat(testDefaultTemplates.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testDefaultTemplates.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testDefaultTemplates.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testDefaultTemplates.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingDefaultTemplates() throws Exception {
        int databaseSizeBeforeUpdate = defaultTemplatesRepository.findAll().size();

        // Create the DefaultTemplates
        DefaultTemplatesDTO defaultTemplatesDTO = defaultTemplatesMapper.toDto(defaultTemplates);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDefaultTemplatesMockMvc.perform(put("/api/default-templates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(defaultTemplatesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DefaultTemplates in the database
        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDefaultTemplates() throws Exception {
        // Initialize the database
        defaultTemplatesRepository.saveAndFlush(defaultTemplates);

        int databaseSizeBeforeDelete = defaultTemplatesRepository.findAll().size();

        // Delete the defaultTemplates
        restDefaultTemplatesMockMvc.perform(delete("/api/default-templates/{id}", defaultTemplates.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DefaultTemplates> defaultTemplatesList = defaultTemplatesRepository.findAll();
        assertThat(defaultTemplatesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
