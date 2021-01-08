package com.quikr.jobs.tools.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class DefaultTemplatesFieldsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefaultTemplatesFields.class);
        DefaultTemplatesFields defaultTemplatesFields1 = new DefaultTemplatesFields();
        defaultTemplatesFields1.setId(1L);
        DefaultTemplatesFields defaultTemplatesFields2 = new DefaultTemplatesFields();
        defaultTemplatesFields2.setId(defaultTemplatesFields1.getId());
        assertThat(defaultTemplatesFields1).isEqualTo(defaultTemplatesFields2);
        defaultTemplatesFields2.setId(2L);
        assertThat(defaultTemplatesFields1).isNotEqualTo(defaultTemplatesFields2);
        defaultTemplatesFields1.setId(null);
        assertThat(defaultTemplatesFields1).isNotEqualTo(defaultTemplatesFields2);
    }
}
