package com.quikr.jobs.tools.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class DefaultTemplatesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefaultTemplatesDTO.class);
        DefaultTemplatesDTO defaultTemplatesDTO1 = new DefaultTemplatesDTO();
        defaultTemplatesDTO1.setId(1L);
        DefaultTemplatesDTO defaultTemplatesDTO2 = new DefaultTemplatesDTO();
        assertThat(defaultTemplatesDTO1).isNotEqualTo(defaultTemplatesDTO2);
        defaultTemplatesDTO2.setId(defaultTemplatesDTO1.getId());
        assertThat(defaultTemplatesDTO1).isEqualTo(defaultTemplatesDTO2);
        defaultTemplatesDTO2.setId(2L);
        assertThat(defaultTemplatesDTO1).isNotEqualTo(defaultTemplatesDTO2);
        defaultTemplatesDTO1.setId(null);
        assertThat(defaultTemplatesDTO1).isNotEqualTo(defaultTemplatesDTO2);
    }
}
