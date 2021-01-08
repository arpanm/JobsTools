package com.quikr.jobs.tools.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DefaultTemplates.
 */
@Entity
@Table(name = "default_templates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DefaultTemplates implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "content_style", nullable = false)
    private String contentStyle;

    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "approved_template_id", nullable = false)
    private String approvedTemplateId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "default_templates_default_templates_fields",
               joinColumns = @JoinColumn(name = "default_templates_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "default_templates_fields_id", referencedColumnName = "id"))
    private Set<DefaultTemplatesFields> defaultTemplatesFields = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DefaultTemplates name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentStyle() {
        return contentStyle;
    }

    public DefaultTemplates contentStyle(String contentStyle) {
        this.contentStyle = contentStyle;
        return this;
    }

    public void setContentStyle(String contentStyle) {
        this.contentStyle = contentStyle;
    }

    public String getComments() {
        return comments;
    }

    public DefaultTemplates comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getApprovedTemplateId() {
        return approvedTemplateId;
    }

    public DefaultTemplates approvedTemplateId(String approvedTemplateId) {
        this.approvedTemplateId = approvedTemplateId;
        return this;
    }

    public void setApprovedTemplateId(String approvedTemplateId) {
        this.approvedTemplateId = approvedTemplateId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DefaultTemplates createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public DefaultTemplates createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public DefaultTemplates updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public DefaultTemplates updatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<DefaultTemplatesFields> getDefaultTemplatesFields() {
        return defaultTemplatesFields;
    }

    public DefaultTemplates defaultTemplatesFields(Set<DefaultTemplatesFields> defaultTemplatesFields) {
        this.defaultTemplatesFields = defaultTemplatesFields;
        return this;
    }

    public DefaultTemplates addDefaultTemplatesFields(DefaultTemplatesFields defaultTemplatesFields) {
        this.defaultTemplatesFields.add(defaultTemplatesFields);
        defaultTemplatesFields.getDefaultTemplates().add(this);
        return this;
    }

    public DefaultTemplates removeDefaultTemplatesFields(DefaultTemplatesFields defaultTemplatesFields) {
        this.defaultTemplatesFields.remove(defaultTemplatesFields);
        defaultTemplatesFields.getDefaultTemplates().remove(this);
        return this;
    }

    public void setDefaultTemplatesFields(Set<DefaultTemplatesFields> defaultTemplatesFields) {
        this.defaultTemplatesFields = defaultTemplatesFields;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultTemplates)) {
            return false;
        }
        return id != null && id.equals(((DefaultTemplates) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefaultTemplates{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contentStyle='" + getContentStyle() + "'" +
            ", comments='" + getComments() + "'" +
            ", approvedTemplateId='" + getApprovedTemplateId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
