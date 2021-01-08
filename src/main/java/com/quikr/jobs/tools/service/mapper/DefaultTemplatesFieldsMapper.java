package com.quikr.jobs.tools.service.mapper;


import com.quikr.jobs.tools.domain.*;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesFieldsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DefaultTemplatesFields} and its DTO {@link DefaultTemplatesFieldsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DefaultTemplatesFieldsMapper extends EntityMapper<DefaultTemplatesFieldsDTO, DefaultTemplatesFields> {


    @Mapping(target = "defaultTemplates", ignore = true)
    @Mapping(target = "removeDefaultTemplates", ignore = true)
    DefaultTemplatesFields toEntity(DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO);

    default DefaultTemplatesFields fromId(Long id) {
        if (id == null) {
            return null;
        }
        DefaultTemplatesFields defaultTemplatesFields = new DefaultTemplatesFields();
        defaultTemplatesFields.setId(id);
        return defaultTemplatesFields;
    }
}
