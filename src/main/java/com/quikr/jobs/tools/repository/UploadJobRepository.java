package com.quikr.jobs.tools.repository;

import com.quikr.jobs.tools.domain.UploadJob;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UploadJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UploadJobRepository extends JpaRepository<UploadJob, Long> {
}
