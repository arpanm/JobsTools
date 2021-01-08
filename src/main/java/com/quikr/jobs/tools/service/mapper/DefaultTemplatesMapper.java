package com.quikr.jobs.tools.service.mapper;


import com.quikr.jobs.tools.domain.*;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DefaultTemplates} and its DTO {@link DefaultTemplatesDTO}.
 */
@Mapper(componentModel = "spring", uses = {DefaultTemplatesFieldsMapper.class})
public interface DefaultTemplatesMapper extends EntityMapper<DefaultTemplatesDTO, DefaultTemplates> {


    @Mapping(target = "removeDefaultTemplatesFields", ignore = true)

    default DefaultTemplates fromId(Long id) {
        if (id == null) {
            return null;
        }
        DefaultTemplates defaultTemplates = new DefaultTemplates();
        defaultTemplates.setId(id);
        return defaultTemplates;
    }
}
