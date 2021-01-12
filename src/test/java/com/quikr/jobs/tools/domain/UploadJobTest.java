package com.quikr.jobs.tools.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class UploadJobTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UploadJob.class);
        UploadJob uploadJob1 = new UploadJob();
        uploadJob1.setId(1L);
        UploadJob uploadJob2 = new UploadJob();
        uploadJob2.setId(uploadJob1.getId());
        assertThat(uploadJob1).isEqualTo(uploadJob2);
        uploadJob2.setId(2L);
        assertThat(uploadJob1).isNotEqualTo(uploadJob2);
        uploadJob1.setId(null);
        assertThat(uploadJob1).isNotEqualTo(uploadJob2);
    }
}
