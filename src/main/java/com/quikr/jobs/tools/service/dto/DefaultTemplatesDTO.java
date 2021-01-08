package com.quikr.jobs.tools.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.quikr.jobs.tools.domain.DefaultTemplates} entity.
 */
public class DefaultTemplatesDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contentStyle;

    private String comments;

    @NotNull
    private String approvedTemplateId;

    private String createdBy;

    private LocalDate createdOn;

    private String updatedBy;

    private LocalDate updatedOn;

    private Set<DefaultTemplatesFieldsDTO> defaultTemplatesFields = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentStyle() {
        return contentStyle;
    }

    public void setContentStyle(String contentStyle) {
        this.contentStyle = contentStyle;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApprovedTemplateId() {
        return approvedTemplateId;
    }

    public void setApprovedTemplateId(String approvedTemplateId) {
        this.approvedTemplateId = approvedTemplateId;
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

    public Set<DefaultTemplatesFieldsDTO> getDefaultTemplatesFields() {
        return defaultTemplatesFields;
    }

    public void setDefaultTemplatesFields(Set<DefaultTemplatesFieldsDTO> defaultTemplatesFields) {
        this.defaultTemplatesFields = defaultTemplatesFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultTemplatesDTO)) {
            return false;
        }

        return id != null && id.equals(((DefaultTemplatesDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefaultTemplatesDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contentStyle='" + getContentStyle() + "'" +
            ", comments='" + getComments() + "'" +
            ", approvedTemplateId='" + getApprovedTemplateId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", defaultTemplatesFields='" + getDefaultTemplatesFields() + "'" +
            "}";
    }
}
