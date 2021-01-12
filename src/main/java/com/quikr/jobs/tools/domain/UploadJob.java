package com.quikr.jobs.tools.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.quikr.jobs.tools.domain.enumeration.UploadType;

import com.quikr.jobs.tools.domain.enumeration.UploadStatus;

/**
 * A UploadJob.
 */
@Entity
@Table(name = "upload_job")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UploadJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UploadType type;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UploadStatus status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @OneToMany(mappedBy = "jobId")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<QCashUploadRow> qCashUploadRows = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UploadType getType() {
        return type;
    }

    public UploadJob type(UploadType type) {
        this.type = type;
        return this;
    }

    public void setType(UploadType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public UploadJob url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UploadStatus getStatus() {
        return status;
    }

    public UploadJob status(UploadStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(UploadStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UploadJob createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public UploadJob createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public UploadJob updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public UploadJob updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<QCashUploadRow> getQCashUploadRows() {
        return qCashUploadRows;
    }

    public UploadJob qCashUploadRows(Set<QCashUploadRow> qCashUploadRows) {
        this.qCashUploadRows = qCashUploadRows;
        return this;
    }

    public UploadJob addQCashUploadRow(QCashUploadRow qCashUploadRow) {
        this.qCashUploadRows.add(qCashUploadRow);
        qCashUploadRow.setJobId(this);
        return this;
    }

    public UploadJob removeQCashUploadRow(QCashUploadRow qCashUploadRow) {
        this.qCashUploadRows.remove(qCashUploadRow);
        qCashUploadRow.setJobId(null);
        return this;
    }

    public void setQCashUploadRows(Set<QCashUploadRow> qCashUploadRows) {
        this.qCashUploadRows = qCashUploadRows;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UploadJob)) {
            return false;
        }
        return id != null && id.equals(((UploadJob) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UploadJob{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", url='" + getUrl() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
