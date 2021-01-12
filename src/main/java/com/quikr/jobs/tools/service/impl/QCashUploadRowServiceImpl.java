package com.quikr.jobs.tools.service.impl;

import com.quikr.jobs.tools.service.QCashUploadRowService;
import com.quikr.jobs.tools.domain.QCashUploadRow;
import com.quikr.jobs.tools.repository.QCashUploadRowRepository;
import com.quikr.jobs.tools.service.dto.QCashUploadRowDTO;
import com.quikr.jobs.tools.service.mapper.QCashUploadRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QCashUploadRow}.
 */
@Service
@Transactional
public class QCashUploadRowServiceImpl implements QCashUploadRowService {

    private final Logger log = LoggerFactory.getLogger(QCashUploadRowServiceImpl.class);

    private final QCashUploadRowRepository qCashUploadRowRepository;

    private final QCashUploadRowMapper qCashUploadRowMapper;

    public QCashUploadRowServiceImpl(QCashUploadRowRepository qCashUploadRowRepository, QCashUploadRowMapper qCashUploadRowMapper) {
        this.qCashUploadRowRepository = qCashUploadRowRepository;
        this.qCashUploadRowMapper = qCashUploadRowMapper;
    }

    @Override
    public QCashUploadRowDTO save(QCashUploadRowDTO qCashUploadRowDTO) {
        log.debug("Request to save QCashUploadRow : {}", qCashUploadRowDTO);
        QCashUploadRow qCashUploadRow = qCashUploadRowMapper.toEntity(qCashUploadRowDTO);
        qCashUploadRow = qCashUploadRowRepository.save(qCashUploadRow);
        return qCashUploadRowMapper.toDto(qCashUploadRow);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QCashUploadRowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QCashUploadRows");
        return qCashUploadRowRepository.findAll(pageable)
            .map(qCashUploadRowMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QCashUploadRowDTO> findOne(Long id) {
        log.debug("Request to get QCashUploadRow : {}", id);
        return qCashUploadRowRepository.findById(id)
            .map(qCashUploadRowMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QCashUploadRow : {}", id);
        qCashUploadRowRepository.deleteById(id);
    }
}
