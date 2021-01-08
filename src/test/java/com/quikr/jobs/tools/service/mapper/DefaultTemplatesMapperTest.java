package com.quikr.jobs.tools.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DefaultTemplatesMapperTest {

    private DefaultTemplatesMapper defaultTemplatesMapper;

    @BeforeEach
    public void setUp() {
        defaultTemplatesMapper = new DefaultTemplatesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(defaultTemplatesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(defaultTemplatesMapper.fromId(null)).isNull();
    }
}
