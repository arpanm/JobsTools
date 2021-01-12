package com.quikr.jobs.tools.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class QCashUploadRowDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QCashUploadRowDTO.class);
        QCashUploadRowDTO qCashUploadRowDTO1 = new QCashUploadRowDTO();
        qCashUploadRowDTO1.setId(1L);
        QCashUploadRowDTO qCashUploadRowDTO2 = new QCashUploadRowDTO();
        assertThat(qCashUploadRowDTO1).isNotEqualTo(qCashUploadRowDTO2);
        qCashUploadRowDTO2.setId(qCashUploadRowDTO1.getId());
        assertThat(qCashUploadRowDTO1).isEqualTo(qCashUploadRowDTO2);
        qCashUploadRowDTO2.setId(2L);
        assertThat(qCashUploadRowDTO1).isNotEqualTo(qCashUploadRowDTO2);
        qCashUploadRowDTO1.setId(null);
        assertThat(qCashUploadRowDTO1).isNotEqualTo(qCashUploadRowDTO2);
    }
}
