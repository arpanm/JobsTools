package com.quikr.jobs.tools.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UploadJobMapperTest {

    private UploadJobMapper uploadJobMapper;

    @BeforeEach
    public void setUp() {
        uploadJobMapper = new UploadJobMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(uploadJobMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(uploadJobMapper.fromId(null)).isNull();
    }
}
