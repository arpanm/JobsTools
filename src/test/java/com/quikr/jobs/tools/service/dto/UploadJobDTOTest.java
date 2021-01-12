package com.quikr.jobs.tools.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class UploadJobDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadJobDTO.class);
        UploadJobDTO uploadJobDTO1 = new UploadJobDTO();
        uploadJobDTO1.setId(1L);
        UploadJobDTO uploadJobDTO2 = new UploadJobDTO();
        assertThat(uploadJobDTO1).isNotEqualTo(uploadJobDTO2);
        uploadJobDTO2.setId(uploadJobDTO1.getId());
        assertThat(uploadJobDTO1).isEqualTo(uploadJobDTO2);
        uploadJobDTO2.setId(2L);
        assertThat(uploadJobDTO1).isNotEqualTo(uploadJobDTO2);
        uploadJobDTO1.setId(null);
        assertThat(uploadJobDTO1).isNotEqualTo(uploadJobDTO2);
    }
}
