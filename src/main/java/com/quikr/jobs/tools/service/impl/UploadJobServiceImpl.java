package com.quikr.jobs.tools.service.impl;

import com.quikr.jobs.tools.service.UploadJobService;
import com.quikr.jobs.tools.domain.UploadJob;
import com.quikr.jobs.tools.repository.UploadJobRepository;
import com.quikr.jobs.tools.service.dto.UploadJobDTO;
import com.quikr.jobs.tools.service.mapper.UploadJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UploadJob}.
 */
@Service
@Transactional
public class UploadJobServiceImpl implements UploadJobService {

    private final Logger log = LoggerFactory.getLogger(UploadJobServiceImpl.class);

    private final UploadJobRepository uploadJobRepository;

    private final UploadJobMapper uploadJobMapper;

    public UploadJobServiceImpl(UploadJobRepository uploadJobRepository, UploadJobMapper uploadJobMapper) {
        this.uploadJobRepository = uploadJobRepository;
        this.uploadJobMapper = uploadJobMapper;
    }

    @Override
    public UploadJobDTO save(UploadJobDTO uploadJobDTO) {
        log.debug("Request to save UploadJob : {}", uploadJobDTO);
        UploadJob uploadJob = uploadJobMapper.toEntity(uploadJobDTO);
        uploadJob = uploadJobRepository.save(uploadJob);
        return uploadJobMapper.toDto(uploadJob);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UploadJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UploadJobs");
        return uploadJobRepository.findAll(pageable)
            .map(uploadJobMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UploadJobDTO> findOne(Long id) {
        log.debug("Request to get UploadJob : {}", id);
        return uploadJobRepository.findById(id)
            .map(uploadJobMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UploadJob : {}", id);
        uploadJobRepository.deleteById(id);
    }
}
