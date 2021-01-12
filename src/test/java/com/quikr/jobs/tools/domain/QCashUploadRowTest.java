package com.quikr.jobs.tools.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.quikr.jobs.tools.web.rest.TestUtil;

public class QCashUploadRowTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QCashUploadRow.class);
        QCashUploadRow qCashUploadRow1 = new QCashUploadRow();
        qCashUploadRow1.setId(1L);
        QCashUploadRow qCashUploadRow2 = new QCashUploadRow();
        qCashUploadRow2.setId(qCashUploadRow1.getId());
        assertThat(qCashUploadRow1).isEqualTo(qCashUploadRow2);
        qCashUploadRow2.setId(2L);
        assertThat(qCashUploadRow1).isNotEqualTo(qCashUploadRow2);
        qCashUploadRow1.setId(null);
        assertThat(qCashUploadRow1).isNotEqualTo(qCashUploadRow2);
    }
}
