package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.JobsOpsToolApp;
import com.quikr.jobs.tools.domain.UploadJob;
import com.quikr.jobs.tools.repository.UploadJobRepository;
import com.quikr.jobs.tools.service.UploadJobService;
import com.quikr.jobs.tools.service.dto.UploadJobDTO;
import com.quikr.jobs.tools.service.mapper.UploadJobMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.quikr.jobs.tools.domain.enumeration.UploadType;
import com.quikr.jobs.tools.domain.enumeration.UploadStatus;
/**
 * Integration tests for the {@link UploadJobResource} REST controller.
 */
@SpringBootTest(classes = JobsOpsToolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UploadJobResourceIT {

    private static final UploadType DEFAULT_TYPE = UploadType.QCash;
    private static final UploadType UPDATED_TYPE = UploadType.Other;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final UploadStatus DEFAULT_STATUS = UploadStatus.INIT;
    private static final UploadStatus UPDATED_STATUS = UploadStatus.PROCESSING;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UploadJobRepository uploadJobRepository;

    @Autowired
    private UploadJobMapper uploadJobMapper;

    @Autowired
    private UploadJobService uploadJobService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUploadJobMockMvc;

    private UploadJob uploadJob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UploadJob createEntity(EntityManager em) {
        UploadJob uploadJob = new UploadJob()
            .type(DEFAULT_TYPE)
            .url(DEFAULT_URL)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return uploadJob;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UploadJob createUpdatedEntity(EntityManager em) {
        UploadJob uploadJob = new UploadJob()
            .type(UPDATED_TYPE)
            .url(UPDATED_URL)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return uploadJob;
    }

    @BeforeEach
    public void initTest() {
        uploadJob = createEntity(em);
    }

    @Test
    @Transactional
    public void createUploadJob() throws Exception {
        int databaseSizeBeforeCreate = uploadJobRepository.findAll().size();
        // Create the UploadJob
        UploadJobDTO uploadJobDTO = uploadJobMapper.toDto(uploadJob);
        restUploadJobMockMvc.perform(post("/api/upload-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uploadJobDTO)))
            .andExpect(status().isCreated());

        // Validate the UploadJob in the database
        List<UploadJob> uploadJobList = uploadJobRepository.findAll();
        assertThat(uploadJobList).hasSize(databaseSizeBeforeCreate + 1);
        UploadJob testUploadJob = uploadJobList.get(uploadJobList.size() - 1);
        assertThat(testUploadJob.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUploadJob.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testUploadJob.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUploadJob.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUploadJob.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testUploadJob.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testUploadJob.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createUploadJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uploadJobRepository.findAll().size();

        // Create the UploadJob with an existing ID
        uploadJob.setId(1L);
        UploadJobDTO uploadJobDTO = uploadJobMapper.toDto(uploadJob);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUploadJobMockMvc.perform(post("/api/upload-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uploadJobDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UploadJob in the database
        List<UploadJob> uploadJobList = uploadJobRepository.findAll();
        assertThat(uploadJobList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUploadJobs() throws Exception {
        // Initialize the database
        uploadJobRepository.saveAndFlush(uploadJob);

        // Get all the uploadJobList
        restUploadJobMockMvc.perform(get("/api/upload-jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uploadJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getUploadJob() throws Exception {
        // Initialize the database
        uploadJobRepository.saveAndFlush(uploadJob);

        // Get the uploadJob
        restUploadJobMockMvc.perform(get("/api/upload-jobs/{id}", uploadJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uploadJob.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUploadJob() throws Exception {
        // Get the uploadJob
        restUploadJobMockMvc.perform(get("/api/upload-jobs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUploadJob() throws Exception {
        // Initialize the database
        uploadJobRepository.saveAndFlush(uploadJob);

        int databaseSizeBeforeUpdate = uploadJobRepository.findAll().size();

        // Update the uploadJob
        UploadJob updatedUploadJob = uploadJobRepository.findById(uploadJob.getId()).get();
        // Disconnect from session so that the updates on updatedUploadJob are not directly saved in db
        em.detach(updatedUploadJob);
        updatedUploadJob
            .type(UPDATED_TYPE)
            .url(UPDATED_URL)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        UploadJobDTO uploadJobDTO = uploadJobMapper.toDto(updatedUploadJob);

        restUploadJobMockMvc.perform(put("/api/upload-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uploadJobDTO)))
            .andExpect(status().isOk());

        // Validate the UploadJob in the database
        List<UploadJob> uploadJobList = uploadJobRepository.findAll();
        assertThat(uploadJobList).hasSize(databaseSizeBeforeUpdate);
        UploadJob testUploadJob = uploadJobList.get(uploadJobList.size() - 1);
        assertThat(testUploadJob.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUploadJob.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testUploadJob.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUploadJob.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUploadJob.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUploadJob.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testUploadJob.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingUploadJob() throws Exception {
        int databaseSizeBeforeUpdate = uploadJobRepository.findAll().size();

        // Create the UploadJob
        UploadJobDTO uploadJobDTO = uploadJobMapper.toDto(uploadJob);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUploadJobMockMvc.perform(put("/api/upload-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uploadJobDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UploadJob in the database
        List<UploadJob> uploadJobList = uploadJobRepository.findAll();
        assertThat(uploadJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUploadJob() throws Exception {
        // Initialize the database
        uploadJobRepository.saveAndFlush(uploadJob);

        int databaseSizeBeforeDelete = uploadJobRepository.findAll().size();

        // Delete the uploadJob
        restUploadJobMockMvc.perform(delete("/api/upload-jobs/{id}", uploadJob.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UploadJob> uploadJobList = uploadJobRepository.findAll();
        assertThat(uploadJobList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
