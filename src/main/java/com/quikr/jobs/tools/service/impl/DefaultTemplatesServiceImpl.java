package com.quikr.jobs.tools.service.impl;

import com.quikr.jobs.tools.service.DefaultTemplatesService;
import com.quikr.jobs.tools.domain.DefaultTemplates;
import com.quikr.jobs.tools.repository.DefaultTemplatesRepository;
import com.quikr.jobs.tools.service.dto.DefaultTemplatesDTO;
import com.quikr.jobs.tools.service.mapper.DefaultTemplatesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DefaultTemplates}.
 */
@Service
@Transactional
public class DefaultTemplatesServiceImpl implements DefaultTemplatesService {

    private final Logger log = LoggerFactory.getLogger(DefaultTemplatesServiceImpl.class);

    private final DefaultTemplatesRepository defaultTemplatesRepository;

    private final DefaultTemplatesMapper defaultTemplatesMapper;

    public DefaultTemplatesServiceImpl(DefaultTemplatesRepository defaultTemplatesRepository, DefaultTemplatesMapper defaultTemplatesMapper) {
        this.defaultTemplatesRepository = defaultTemplatesRepository;
        this.defaultTemplatesMapper = defaultTemplatesMapper;
    }

    @Override
    public DefaultTemplatesDTO save(DefaultTemplatesDTO defaultTemplatesDTO) {
        log.debug("Request to save DefaultTemplates : {}", defaultTemplatesDTO);
        DefaultTemplates defaultTemplates = defaultTemplatesMapper.toEntity(defaultTemplatesDTO);
        defaultTemplates = defaultTemplatesRepository.save(defaultTemplates);
        return defaultTemplatesMapper.toDto(defaultTemplates);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DefaultTemplatesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DefaultTemplates");
        return defaultTemplatesRepository.findAll(pageable)
            .map(defaultTemplatesMapper::toDto);
    }


    public Page<DefaultTemplatesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return defaultTemplatesRepository.findAllWithEagerRelationships(pageable).map(defaultTemplatesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DefaultTemplatesDTO> findOne(Long id) {
        log.debug("Request to get DefaultTemplates : {}", id);
        return defaultTemplatesRepository.findOneWithEagerRelationships(id)
            .map(defaultTemplatesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DefaultTemplates : {}", id);
        defaultTemplatesRepository.deleteById(id);
    }
}
