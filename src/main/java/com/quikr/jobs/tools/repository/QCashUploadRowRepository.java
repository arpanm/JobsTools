package com.quikr.jobs.tools.repository;

import com.quikr.jobs.tools.domain.QCashUploadRow;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QCashUploadRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QCashUploadRowRepository extends JpaRepository<QCashUploadRow, Long> {
}
