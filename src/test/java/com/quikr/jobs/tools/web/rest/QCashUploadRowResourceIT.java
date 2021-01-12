package com.quikr.jobs.tools.web.rest;

import com.quikr.jobs.tools.JobsOpsToolApp;
import com.quikr.jobs.tools.domain.QCashUploadRow;
import com.quikr.jobs.tools.repository.QCashUploadRowRepository;
import com.quikr.jobs.tools.service.QCashUploadRowService;
import com.quikr.jobs.tools.service.dto.QCashUploadRowDTO;
import com.quikr.jobs.tools.service.mapper.QCashUploadRowMapper;

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

import com.quikr.jobs.tools.domain.enumeration.QCashUploadRowStatus;
/**
 * Integration tests for the {@link QCashUploadRowResource} REST controller.
 */
@SpringBootTest(classes = JobsOpsToolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QCashUploadRowResourceIT {

    private static final Long DEFAULT_BABEL_USER_ID = 1L;
    private static final Long UPDATED_BABEL_USER_ID = 2L;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final QCashUploadRowStatus DEFAULT_STATUS = QCashUploadRowStatus.INIT;
    private static final QCashUploadRowStatus UPDATED_STATUS = QCashUploadRowStatus.PUSHED;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private QCashUploadRowRepository qCashUploadRowRepository;

    @Autowired
    private QCashUploadRowMapper qCashUploadRowMapper;

    @Autowired
    private QCashUploadRowService qCashUploadRowService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQCashUploadRowMockMvc;

    private QCashUploadRow qCashUploadRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QCashUploadRow createEntity(EntityManager em) {
        QCashUploadRow qCashUploadRow = new QCashUploadRow()
            .babelUserId(DEFAULT_BABEL_USER_ID)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .amount(DEFAULT_AMOUNT)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return qCashUploadRow;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QCashUploadRow createUpdatedEntity(EntityManager em) {
        QCashUploadRow qCashUploadRow = new QCashUploadRow()
            .babelUserId(UPDATED_BABEL_USER_ID)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return qCashUploadRow;
    }

    @BeforeEach
    public void initTest() {
        qCashUploadRow = createEntity(em);
    }

    @Test
    @Transactional
    public void createQCashUploadRow() throws Exception {
        int databaseSizeBeforeCreate = qCashUploadRowRepository.findAll().size();
        // Create the QCashUploadRow
        QCashUploadRowDTO qCashUploadRowDTO = qCashUploadRowMapper.toDto(qCashUploadRow);
        restQCashUploadRowMockMvc.perform(post("/api/q-cash-upload-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qCashUploadRowDTO)))
            .andExpect(status().isCreated());

        // Validate the QCashUploadRow in the database
        List<QCashUploadRow> qCashUploadRowList = qCashUploadRowRepository.findAll();
        assertThat(qCashUploadRowList).hasSize(databaseSizeBeforeCreate + 1);
        QCashUploadRow testQCashUploadRow = qCashUploadRowList.get(qCashUploadRowList.size() - 1);
        assertThat(testQCashUploadRow.getBabelUserId()).isEqualTo(DEFAULT_BABEL_USER_ID);
        assertThat(testQCashUploadRow.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testQCashUploadRow.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testQCashUploadRow.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testQCashUploadRow.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testQCashUploadRow.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testQCashUploadRow.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testQCashUploadRow.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testQCashUploadRow.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createQCashUploadRowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qCashUploadRowRepository.findAll().size();

        // Create the QCashUploadRow with an existing ID
        qCashUploadRow.setId(1L);
        QCashUploadRowDTO qCashUploadRowDTO = qCashUploadRowMapper.toDto(qCashUploadRow);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQCashUploadRowMockMvc.perform(post("/api/q-cash-upload-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qCashUploadRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QCashUploadRow in the database
        List<QCashUploadRow> qCashUploadRowList = qCashUploadRowRepository.findAll();
        assertThat(qCashUploadRowList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQCashUploadRows() throws Exception {
        // Initialize the database
        qCashUploadRowRepository.saveAndFlush(qCashUploadRow);

        // Get all the qCashUploadRowList
        restQCashUploadRowMockMvc.perform(get("/api/q-cash-upload-rows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qCashUploadRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].babelUserId").value(hasItem(DEFAULT_BABEL_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getQCashUploadRow() throws Exception {
        // Initialize the database
        qCashUploadRowRepository.saveAndFlush(qCashUploadRow);

        // Get the qCashUploadRow
        restQCashUploadRowMockMvc.perform(get("/api/q-cash-upload-rows/{id}", qCashUploadRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qCashUploadRow.getId().intValue()))
            .andExpect(jsonPath("$.babelUserId").value(DEFAULT_BABEL_USER_ID.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingQCashUploadRow() throws Exception {
        // Get the qCashUploadRow
        restQCashUploadRowMockMvc.perform(get("/api/q-cash-upload-rows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQCashUploadRow() throws Exception {
        // Initialize the database
        qCashUploadRowRepository.saveAndFlush(qCashUploadRow);

        int databaseSizeBeforeUpdate = qCashUploadRowRepository.findAll().size();

        // Update the qCashUploadRow
        QCashUploadRow updatedQCashUploadRow = qCashUploadRowRepository.findById(qCashUploadRow.getId()).get();
        // Disconnect from session so that the updates on updatedQCashUploadRow are not directly saved in db
        em.detach(updatedQCashUploadRow);
        updatedQCashUploadRow
            .babelUserId(UPDATED_BABEL_USER_ID)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        QCashUploadRowDTO qCashUploadRowDTO = qCashUploadRowMapper.toDto(updatedQCashUploadRow);

        restQCashUploadRowMockMvc.perform(put("/api/q-cash-upload-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qCashUploadRowDTO)))
            .andExpect(status().isOk());

        // Validate the QCashUploadRow in the database
        List<QCashUploadRow> qCashUploadRowList = qCashUploadRowRepository.findAll();
        assertThat(qCashUploadRowList).hasSize(databaseSizeBeforeUpdate);
        QCashUploadRow testQCashUploadRow = qCashUploadRowList.get(qCashUploadRowList.size() - 1);
        assertThat(testQCashUploadRow.getBabelUserId()).isEqualTo(UPDATED_BABEL_USER_ID);
        assertThat(testQCashUploadRow.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testQCashUploadRow.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testQCashUploadRow.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testQCashUploadRow.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testQCashUploadRow.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testQCashUploadRow.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testQCashUploadRow.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testQCashUploadRow.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingQCashUploadRow() throws Exception {
        int databaseSizeBeforeUpdate = qCashUploadRowRepository.findAll().size();

        // Create the QCashUploadRow
        QCashUploadRowDTO qCashUploadRowDTO = qCashUploadRowMapper.toDto(qCashUploadRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQCashUploadRowMockMvc.perform(put("/api/q-cash-upload-rows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(qCashUploadRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QCashUploadRow in the database
        List<QCashUploadRow> qCashUploadRowList = qCashUploadRowRepository.findAll();
        assertThat(qCashUploadRowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQCashUploadRow() throws Exception {
        // Initialize the database
        qCashUploadRowRepository.saveAndFlush(qCashUploadRow);

        int databaseSizeBeforeDelete = qCashUploadRowRepository.findAll().size();

        // Delete the qCashUploadRow
        restQCashUploadRowMockMvc.perform(delete("/api/q-cash-upload-rows/{id}", qCashUploadRow.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QCashUploadRow> qCashUploadRowList = qCashUploadRowRepository.findAll();
        assertThat(qCashUploadRowList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
