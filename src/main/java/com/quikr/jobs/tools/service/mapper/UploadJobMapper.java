package com.quikr.jobs.tools.service.mapper;


import com.quikr.jobs.tools.domain.*;
import com.quikr.jobs.tools.service.dto.UploadJobDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UploadJob} and its DTO {@link UploadJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UploadJobMapper extends EntityMapper<UploadJobDTO, UploadJob> {


    @Mapping(target = "qCashUploadRows", ignore = true)
    @Mapping(target = "removeQCashUploadRow", ignore = true)
    UploadJob toEntity(UploadJobDTO uploadJobDTO);

    default UploadJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        UploadJob uploadJob = new UploadJob();
        uploadJob.setId(id);
        return uploadJob;
    }
}
