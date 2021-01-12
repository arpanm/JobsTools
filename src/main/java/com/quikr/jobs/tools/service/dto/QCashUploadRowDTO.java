package com.quikr.jobs.tools.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import com.quikr.jobs.tools.domain.enumeration.QCashUploadRowStatus;

/**
 * A DTO for the {@link com.quikr.jobs.tools.domain.QCashUploadRow} entity.
 */
public class QCashUploadRowDTO implements Serializable {
    
    private Long id;

    private Long babelUserId;

    private String email;

    private Long phone;

    private Float amount;

    private QCashUploadRowStatus status;

    private String createdBy;

    private LocalDate createdOn;

    private String updatedBy;

    private LocalDate updatedOn;


    private Long jobIdId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBabelUserId() {
        return babelUserId;
    }

    public void setBabelUserId(Long babelUserId) {
        this.babelUserId = babelUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public QCashUploadRowStatus getStatus() {
        return status;
    }

    public void setStatus(QCashUploadRowStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getJobIdId() {
        return jobIdId;
    }

    public void setJobIdId(Long uploadJobId) {
        this.jobIdId = uploadJobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QCashUploadRowDTO)) {
            return false;
        }

        return id != null && id.equals(((QCashUploadRowDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QCashUploadRowDTO{" +
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
            ", jobIdId=" + getJobIdId() +
            "}";
    }
}
