package com.quikr.jobs.tools.service.impl;

import com.quikr.jobs.tools.service.DefaultTemplatesFieldsService;
import com.quikr.jobs.tools.domain.DefaultTemplatesFields;
import com.quikr.jobs.tools.repository.DefaultTemplatesFieldsRepository;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesFieldsDTO;
import com.quikr.jobs.tools.service.mapper.DefaultTemplatesFieldsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DefaultTemplatesFields}.
 */
@Service
@Transactional
public class DefaultTemplatesFieldsServiceImpl implements DefaultTemplatesFieldsService {

    private final Logger log = LoggerFactory.getLogger(DefaultTemplatesFieldsServiceImpl.class);

    private final DefaultTemplatesFieldsRepository defaultTemplatesFieldsRepository;

    private final DefaultTemplatesFieldsMapper defaultTemplatesFieldsMapper;

    public DefaultTemplatesFieldsServiceImpl(DefaultTemplatesFieldsRepository defaultTemplatesFieldsRepository, DefaultTemplatesFieldsMapper defaultTemplatesFieldsMapper) {
        this.defaultTemplatesFieldsRepository = defaultTemplatesFieldsRepository;
        this.defaultTemplatesFieldsMapper = defaultTemplatesFieldsMapper;
    }

    @Override
    public DefaultTemplatesFieldsDTO save(DefaultTemplatesFieldsDTO defaultTemplatesFieldsDTO) {
        log.debug("Request to save DefaultTemplatesFields : {}", defaultTemplatesFieldsDTO);
        DefaultTemplatesFields defaultTemplatesFields = defaultTemplatesFieldsMapper.toEntity(defaultTemplatesFieldsDTO);
        defaultTemplatesFields = defaultTemplatesFieldsRepository.save(defaultTemplatesFields);
        return defaultTemplatesFieldsMapper.toDto(defaultTemplatesFields);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DefaultTemplatesFieldsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DefaultTemplatesFields");
        return defaultTemplatesFieldsRepository.findAll(pageable)
            .map(defaultTemplatesFieldsMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DefaultTemplatesFieldsDTO> findOne(Long id) {
        log.debug("Request to get DefaultTemplatesFields : {}", id);
        return defaultTemplatesFieldsRepository.findById(id)
            .map(defaultTemplatesFieldsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DefaultTemplatesFields : {}", id);
        defaultTemplatesFieldsRepository.deleteById(id);
    }
}
