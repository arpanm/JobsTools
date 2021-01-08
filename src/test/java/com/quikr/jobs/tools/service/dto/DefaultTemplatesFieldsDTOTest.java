package com.quikr.jobs.tools.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class DefaultTemplatesFieldsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefaultTemplatesFieldsDTO.class);
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO1 = new DefaultTemplatesFieldsDTO();
        defaultTemplatesFieldsDTO1.setId(1L);
        DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO2 = new DefaultTemplatesFieldsDTO();
        assertThat(defaultTemplatesFieldsDTO1).isNotEqualTo(defaultTemplatesFieldsDTO2);
        defaultTemplatesFieldsDTO2.setId(defaultTemplatesFieldsDTO1.getId());
        assertThat(defaultTemplatesFieldsDTO1).isEqualTo(defaultTemplatesFieldsDTO2);
        defaultTemplatesFieldsDTO2.setId(2L);
        assertThat(defaultTemplatesFieldsDTO1).isNotEqualTo(defaultTemplatesFieldsDTO2);
        defaultTemplatesFieldsDTO1.setId(null);
        assertThat(defaultTemplatesFieldsDTO1).isNotEqualTo(defaultTemplatesFieldsDTO2);
    }
}
