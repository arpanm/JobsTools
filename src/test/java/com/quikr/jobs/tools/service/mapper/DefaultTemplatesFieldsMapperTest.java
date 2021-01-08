package com.quikr.jobs.tools.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultTemplatesFieldsMapperTest {

    private DefaultTemplatesFieldsMapper defaultTemplatesFieldsMapper;

    @BeforeEach
    public void setUp() {
        defaultTemplatesFieldsMapper = new DefaultTemplatesFieldsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(defaultTemplatesFieldsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(defaultTemplatesFieldsMapper.fromId(null)).isNull();
    }
}
