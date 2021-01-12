package com.quikr.jobs.tools.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class QCashUploadRowMapperTest {

    private QCashUploadRowMapper qCashUploadRowMapper;

    @BeforeEach
    public void setUp() {
        qCashUploadRowMapper = new QCashUploadRowMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(qCashUploadRowMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qCashUploadRowMapper.fromId(null)).isNull();
    }
}
