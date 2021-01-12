package com.quikr.jobs.tools.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.quikr.jobs.tools.domain.enumeration.QCashUploadRowStatus;

/**
 * A QCashUploadRow.
 */
@Entity
@Table(name = "q_cash_upload_row")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QCashUploadRow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "babel_user_id")
    private Long babelUserId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "amount")
    private Float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QCashUploadRowStatus status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToOne
    @JsonIgnoreProperties(value = "qCashUploadRows", allowSetters = true)
    private UploadJob jobId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBabelUserId() {
        return babelUserId;
    }

    public QCashUploadRow babelUserId(Long babelUserId) {
        this.babelUserId = babelUserId;
        return this;
    }

    public void setBabelUserId(Long babelUserId) {
        this.babelUserId = babelUserId;
    }

    public String getEmail() {
        return email;
    }

    public QCashUploadRow email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public QCashUploadRow phone(Long phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Float getAmount() {
        return amount;
    }

    public QCashUploadRow amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public QCashUploadRowStatus getStatus() {
        return status;
    }

    public QCashUploadRow status(QCashUploadRowStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(QCashUploadRowStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public QCashUploadRow createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public QCashUploadRow createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public QCashUploadRow updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public QCashUploadRow updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public UploadJob getJobId() {
        return jobId;
    }

    public QCashUploadRow jobId(UploadJob uploadJob) {
        this.jobId = uploadJob;
        return this;
    }

    public void setJobId(UploadJob uploadJob) {
        this.jobId = uploadJob;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QCashUploadRow)) {
            return false;
        }
        return id != null && id.equals(((QCashUploadRow) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QCashUploadRow{" +
            "id=" + getId() +
            ", babelUserId=" + getBabelUserId() +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
