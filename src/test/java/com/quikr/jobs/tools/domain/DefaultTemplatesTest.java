package com.quikr.jobs.tools.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class DefaultTemplatesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DefaultTemplates.class);
        DefaultTemplates defaultTemplates1 = new DefaultTemplates();
        defaultTemplates1.setId(1L);
        DefaultTemplates defaultTemplates2 = new DefaultTemplates();
        defaultTemplates2.setId(defaultTemplates1.getId());
        assertThat(defaultTemplates1).isEqualTo(defaultTemplates2);
        defaultTemplates2.setId(2L);
        assertThat(defaultTemplates1).isNotEqualTo(defaultTemplates2);
        defaultTemplates1.setId(null);
        assertThat(defaultTemplates1).isNotEqualTo(defaultTemplates2);
    }
}
