package com.quikr.jobs.tools.service.mapper;


import com.quikr.jobs.tools.domain.*;
import com.quikr.jobs.tools.service.dto.QCashUploadRowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QCashUploadRow} and its DTO {@link QCashUploadRowDTO}.
 */
@Mapper(componentModel = "spring", uses = {UploadJobMapper.class})
public interface QCashUploadRowMapper extends EntityMapper<QCashUploadRowDTO, QCashUploadRow> {

    @Mapping(source = "jobId.id", target = "jobIdId")
    QCashUploadRowDTO toDto(QCashUploadRow qCashUploadRow);

    @Mapping(source = "jobIdId", target = "jobId")
    QCashUploadRow toEntity(QCashUploadRowDTO qCashUploadRowDTO);

    default QCashUploadRow fromId(Long id) {
        if (id == null) {
            return null;
        }
        QCashUploadRow qCashUploadRow = new QCashUploadRow();
        qCashUploadRow.setId(id);
        return qCashUploadRow;
    }
}
